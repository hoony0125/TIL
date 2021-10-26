import audioop              # audio module 음성신호(음성데이터)를 가공
from ctypes import *        # error handler 조작시 사용하는 모듈

import ktkws                # 키워드 검출 모듈 (호출어, 함수)
import MicrophoneStream as MS # 마이크 모듈

KWSID = ['기가지니', '지니야', '친구야', '자기야']

RATE = 16000 # 마이크 입력 비율 16khz
CHUNK = 512  # 마이크 입력 CHUNK(한 호흡에 말하는 길이)

# 음성인식

# 불필요한 에러 메시지 삭제
ERROR_HANDLER_FUNC = CFUNCTYPE(None, c_char_p, c_int, c_char_p, c_int, c_char_p)
def py_error_handler(filename, line, function, err, fmt):
  dummy_var = 0
c_error_handler = ERROR_HANDLER_FUNC(py_error_handler)
asound = cdll.LoadLibrary('libasound.so')
asound.snd_lib_error_set_handler(c_error_handler)


# 음성 감지 함수
def detect():
    with MS.MicrophoneStream(RATE, CHUNK) as stream:
        audio_generator = stream.generator()
        
        # audio_generator부터 음성 데이터를 받아와서 content에 할당
        for content in audio_generator:
            
            # 입력된 음성(content)에 호출어가 포함이 되어 있는지 확인
            rc = ktkws.detect(content)
            
            # 마이크를 통해서 입력되고 있는 음성 데이터의 음량을 숫자로 출력
            rms = audioop.rms(content, 2)
            # print('audio rms = %d' %(rms))
            
            # ktkws를 통해서 호출어가 인식되면 rc에 1이 인식된다
            if rc == 1:
                MS.play_file("../data/sample_sound.wav")
                return 200


def test(key_word = '기가지니'):
    
    # kt 모듈의 초기화
    rc = ktkws.init('../data/kwsmodel.pack')
    
    rc = ktkws.start()
    print('start rc = %d' %(rc))
    print('\호출어를 불러 보세요\n')
    
    ktkws.set_keyword(KWSID.index(key_word))
    rc = detect()
    print('detect rc = %d' %(rc))
    print("\n\n호출어가 정상적으로 인식되었습니다\n\n")
    ktkws.stop()
    

if __name__ == '__main__':
    test()











