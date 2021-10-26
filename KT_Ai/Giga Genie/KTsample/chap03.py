# 음성인식으로 LED 3초간 ON -> OFF
import audioop              # audio module 음성신호(음성데이터)를 가공
from ctypes import *        # error handler 조작시 사용하는 모듈

import ktkws                # 키워드 검출 모듈 (호출어, 함수)
import MicrophoneStream as MS # 마이크 모듈

import RPi.GPIO as GPIO   # 라즈베리파이 GPIO를 사용해서 스위치입력, LED를 제어
import time

KWSID = ['기가지니', '지니야', '친구야', '자기야']

RATE = 16000 # 마이크 입력 비율 16khz
CHUNK = 512  # 마이크 입력 CHUNK(한 호흡에 말하는 길이)

GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO.setup(29, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(31, GPIO.OUT)
btn_status = False

# 불필요한 에러 메시지 삭제
ERROR_HANDLER_FUNC = CFUNCTYPE(None, c_char_p, c_int, c_char_p, c_int, c_char_p)
def py_error_handler(filename, line, function, err, fmt):
  dummy_var = 0
c_error_handler = ERROR_HANDLER_FUNC(py_error_handler)
asound = cdll.LoadLibrary('libasound.so')
asound.snd_lib_error_set_handler(c_error_handler)

# 음성 감지
def detect():
    with MS.MicrophoneStream(RATE, CHUNK) as stream:
        audio_generator = stream.generator()
        
        # audio_generator부터 음성 데이터를 받아와서 content에 할당
        for content in audio_generator:
            GPIO.output(31, GPIO.LOW) # led off
            
            # 입력된 음성(content)에 호출어가 포함이 되어 있는지 확인
            rc = ktkws.detect(content)
            
            # 마이크를 통해서 입력되고 있는 음성 데이터의 음량을 숫자로 출력
            rms = audioop.rms(content, 2)
            # print('audio rms = %d' %(rms))
            
            # ktkws를 통해서 호출어가 인식되면 rc에 1이 인식된다
            if rc == 1:
                GPIO.output(31, GPIO.HIGH)                
                MS.play_file("../data/sample_sound.wav")
                
                print("LED ON!!!\n")
                
                time.sleep(3)
                GPIO.output(31, GPIO.LOW)
                

def test(key_word = '기가지니'):
    
    # kt 모듈의 초기화
    rc = ktkws.init('../data/kwsmodel.pack')
    
    rc = ktkws.start()
    print('start rc = %d' %(rc))
    print('\호출어를 불러 보세요\n')
    
    ktkws.set_keyword(KWSID.index(key_word))
    detect()    
    print("\n\n호출어가 정상적으로 인식되었습니다\n\n")
    ktkws.stop()

test()





