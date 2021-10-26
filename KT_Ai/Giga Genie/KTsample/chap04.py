# 불켜, 불꺼

import grpc # KT AI Server와 통신
import gigagenieRPC_pb2        # KT AI Server 에서 제공하는 함수들의 목록과 요청 및 응답 형식이이 저장되어 있는 모듈 
import gigagenieRPC_pb2_grpc
import MicrophoneStream as MS
import user_auth as UA  # 서비스키 저장 및 관리하는 모듈
import audioop
import os              # 운영체제
from ctypes import *

import RPi.GPIO as GPIO

HOST = 'gate.gigagenie.ai'
PORT = 4080
RATE = 16000
CHUNK = 512

GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO.setup(29, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(31, GPIO.OUT)

# 불필요한 에러 메시지 삭제
ERROR_HANDLER_FUNC = CFUNCTYPE(None, c_char_p, c_int, c_char_p, c_int, c_char_p)
def py_error_handler(filename, line, function, err, fmt):
  dummy_var = 0
c_error_handler = ERROR_HANDLER_FUNC(py_error_handler)
asound = cdll.LoadLibrary('libasound.so')
asound.snd_lib_error_set_handler(c_error_handler)

# 마이크에서 입력되는 음성을 메시지에 저장해서 변환하고 generate()를 사용해서 데이터를 전달한다
def generate_request():
    with MS.MicrophoneStream(RATE, CHUNK) as stream:
        audio_generator = stream.generator()
    
        for content in audio_generator:
            message = gigagenieRPC_pb2.reqVoice()
            message.audioContent = content
            yield message
            
            rms = audioop.rms(content,2)
    
# 음성데이터를 KT Server에 전갈하는 역할
def getVoice2Text():
    print ("\n\n음성인식을 시작합니다.\n\n종료하시려면 Ctrl+\ 키를 누루세요.\n\n\n")
    channel = grpc.secure_channel('{}:{}'.format(HOST, PORT), UA.getCredentials())
    
    stub = gigagenieRPC_pb2_grpc.GigagenieStub(channel)
    
    request = generate_request()
    resultText = ''
    
    for response in stub.getVoice2Text(request):
        if response.resultCd == 200: # partial
            print('resultCd=%d | recognizedText= %s' 
                  % (response.resultCd, response.recognizedText))
            resultText = response.recognizedText
        elif response.resultCd == 201: # final
            print('resultCd=%d | recognizedText= %s' 
                  % (response.resultCd, response.recognizedText))
            resultText = response.recognizedText
            break
        else:
            print('resultCd=%d | recognizedText= %s' 
                  % (response.resultCd, response.recognizedText))
            break

    print ("\n\n인식결과: %s \n\n\n" % (resultText))
    return resultText

def main():
#    text = getVoice2Text()
#    print(text)
    GPIO.output(31, GPIO.LOW)
    
    while True:
        text = getVoice2Text()
        
        if text == '불 켜':
            GPIO.output(31, GPIO.HIGH)
            print('불을 켰습니다')
        elif text == '불 꺼':
            GPIO.output(31, GPIO.LOW)
            print('불을 껏습니다')
    
if __name__=='__main__':
    main()

    















