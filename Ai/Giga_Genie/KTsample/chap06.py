import grpc
import gigagenieRPC_pb2
import gigagenieRPC_pb2_grpc
import MicrophoneStream as MS
import user_auth as UA
import os
from ctypes import *

# 음성 -> 음성
import ex4_getText2VoiceStream as tts
import audioop

RATE = 16000
CHUNK = 512

HOST = 'gate.gigagenie.ai'
PORT = 4080

# 불필요한 에러 메시지 삭제
ERROR_HANDLER_FUNC = CFUNCTYPE(None, c_char_p, c_int, c_char_p, c_int, c_char_p)
def py_error_handler(filename, line, function, err, fmt):
  dummy_var = 0
c_error_handler = ERROR_HANDLER_FUNC(py_error_handler)
asound = cdll.LoadLibrary('libasound.so')
asound.snd_lib_error_set_handler(c_error_handler)

channel = grpc.secure_channel('{}:{}'.format(HOST, PORT), UA.getCredentials())
stub = gigagenieRPC_pb2_grpc.GigagenieStub(channel)

def generate_request():
    with MS.MicrophoneStream(RATE, CHUNK) as stream:
        audio_generator = stream.generator()

        messageReq = gigagenieRPC_pb2.reqQueryVoice()
        messageReq.reqOptions.lang = 0
        messageReq.reqOptions.userSession = "1234"
        messageReq.reqOptions.deviceId="aklsjdnalksd"
        
        yield messageReq
        
        for content in audio_generator:
            message = gigagenieRPC_pb2.reqVoice()
            message.audioContent = content
            yield message
            
            rms = audioop.rms(content,2)

# generate_request 에서 반환된 값을 queryByVoice 함수에 입력해서 답변(텍스트, 음성) 출력
def queryByVoice():
    print ("\n\n\n질의할 내용을 말씀해 보세요.\n\n듣고 있는 중......\n")
    
    request = generate_request()
    
    resultText = ''
    response = stub.queryByVoice(request)

    if response.resultCd == 200:
        print("질의 내용: %s" % (response.uword))
        for a in response.action:
            response = a.mesg
            parsing_resp = response.replace('<![CDATA[', '')   # <![CDATA[안녕하세요!?]]> 
            parsing_resp = response.replace(']]>', '')
            resultText = parsing_resp
            print("\n질의에 대한 답변: " + parsing_resp +'\n\n\n')

    else:
        print("정상적인 음성인식이 되지 않았습니다.")
    
    # text -> wav file create
    tts.getText2VoiceStream(resultText, "result_msg.wav")
    MS.play_file('result_msg.wav')
    

def main():
    queryByVoice()
    
if __name__=='__main__':
    main()
    
    
















