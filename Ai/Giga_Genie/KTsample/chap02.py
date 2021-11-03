from ctypes import *        # error handler 조작시 사용하는 모듈
import MicrophoneStream as MS # 마이크 모듈

import RPi.GPIO as GPIO   # 라즈베리파이 GPIO를 사용해서 스위치입력, LED를 제어

RATE = 16000 # 마이크 입력 비율 16khz
CHUNK = 512  # 마이크 입력 CHUNK(한 호흡에 말하는 길이)

# event

'''
아케이드 스위치와 연결 되어 있는 핀 29번
LED가 연결되어 있는 핀 31번
'''
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO.setup(29, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(31, GPIO.OUT)
btn_status = False

# 콜백함수 : 자동호출 함수
def callback(channel):  
	print("falling edge detected from pin {}".format(channel))
	global btn_status
	btn_status = True
	print(btn_status)

GPIO.add_event_detect(29, GPIO.FALLING, callback=callback, bouncetime=10)

# 불필요한 에러 메시지 삭제
ERROR_HANDLER_FUNC = CFUNCTYPE(None, c_char_p, c_int, c_char_p, c_int, c_char_p)
def py_error_handler(filename, line, function, err, fmt):
  dummy_var = 0
c_error_handler = ERROR_HANDLER_FUNC(py_error_handler)
asound = cdll.LoadLibrary('libasound.so')
asound.snd_lib_error_set_handler(c_error_handler)

def btn_detect():
    global btn_status
    
    with MS.MicrophoneStream(RATE, CHUNK) as stream:
        
        while True:
            # led on
            GPIO.output(31, GPIO.HIGH)
            
            rc = 0
            if btn_status == True:
                rc = 1
                btn_status = False
                
            if rc == 1:
                GPIO.output(31, GPIO.LOW)
                MS.play_file("../data/sample_sound.wav")
                print('\n버튼을 클릭해 보세요 \n')
                

print('\n버튼을 클릭해 보세요 \n')
btn_detect()        

















