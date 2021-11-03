# STT(Speech To Text)

![image-20211103171116683](STT(Speech%20To%20Text).assets/image-20211103171116683.png)

\- 녹음을 하고 들을 수 있고, 저장을 한 후 파일 선택을 하면 바로 업로드를 통해 읽어주는 기능

### CSR(CLOVA Speech Recognition) 설정 및 코드 가져오기 

Ai, Naver API 기능을 사용하기 위해서 콘솔 > Products $ Services에서 AI·NAVER API를 클릭 > 계정에서 변경 버튼 클릭 > 사용하려고 하는 서비스 선택 후 저장

CLOVA Speech Recognition(CSR) 우측의 책 모양의 아이콘을 클릭 > CSR - REST API > CSR REST API v1 클릭 > stt(Speech To Text) 클릭 > 내려서 Java 코드를 복사(마크다운에서는 복사해온 것을 가공해서 컨트롤러로 옮겨 웹에서 기능구현한 형태)



### Backend

- **NaverAiController.java**

```java
// 파일 업로드!
@RestController
public class NaverAiController {

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST)
	public String fileUpload( @RequestParam("uploadFile")	// 프론트에서 name=uploadFile를 받는다
							  MultipartFile uploadFile,
							  HttpServletRequest req, 
							  String message) {
		
		System.out.println("PdsController fileUpload");
		System.out.println("message:" + message );
		// 경로 - tomcat server : 3000에 올리는 것!	
			String uploadPath = req.getServletContext().getRealPath("/upload");	// upload폴더를 만들어야 돼
		// 폴더에도 아래처럼 올릴 수는 있다
		// String uploadPath = "p:\\temp";
					
		// 파일명 취득 
		String filename = uploadFile.getOriginalFilename(); // 이렇게 하면 원본 파일명이 나온다. 
		String filepath = uploadPath + File.separator + filename;
										//  '/'와 동일하다고 알면됨
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
			// DB input
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "fail";
		}
		
		// NAVER AI
		String resp = NaverCloud.processSTT(filepath);
		
		return resp;
	}
}
```



### Frontend

- **STT.html**

  녹음기능은 오픈소스 사용

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STT</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>Simple Recorder JS demo</h1>
     
<div id="controls">
 <button type="button" id="recordButton">Record</button>
 <button type="button" id="pauseButton" disabled>Pause</button>
 <button type="button" id="stopButton" disabled>Stop</button>
</div>
<div id="formats">형식: 샘플 레이트를 보려면 녹음을 시작하세요.</div>

<h3>Recordings</h3>
<ol id="recordingsList"></ol>


<hr>

<h2>file upload</h2>

<form id="upload_file_frm">	
	<input type="file" id="uploadFile" name="uploadFile" accept="*">
	<input type="text" name="message">
	<button type="button" id="uploadFileBtn">파일 업로드</button>
	<p id="uploadResult"></p>
</form>

<p id="msg"></p>




<!-- inserting these scripts at the end to be able to use all the elements in the DOM -->
<script src="https://cdn.rawgit.com/mattdiamond/Recorderjs/08e7abd9/dist/recorder.js"></script>
<script>
URL = window.URL || window.webkitURL;
var gumStream;                      //stream from getUserMedia()
var rec;                            //Recorder.js object
var input;                          //MediaStreamAudioSourceNode we'll be recording
// shim for AudioContext when it's not avb. 
var AudioContext = window.AudioContext || window.webkitAudioContext;
var audioContext //audio context to help us record
var recordButton = document.getElementById("recordButton");
var stopButton = document.getElementById("stopButton");
var pauseButton = document.getElementById("pauseButton");
//add events to those 2 buttons
recordButton.addEventListener("click", startRecording);
stopButton.addEventListener("click", stopRecording);
pauseButton.addEventListener("click", pauseRecording);

function startRecording() {
    console.log("recordButton clicked");
    /*
        Simple constraints object, for more advanced audio features see
        https://addpipe.com/blog/audio-constraints-getusermedia/
    */
    var constraints = { audio: true, video: false }
    /*
        Disable the record button until we get a success or fail from getUserMedia() 
    */
    recordButton.disabled = true;
    stopButton.disabled = false;
    pauseButton.disabled = false
    /*
        We're using the standard promise based getUserMedia() 
        https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
    */
    navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
        console.log("getUserMedia() success, stream created, initializing Recorder.js ...");
        /*
            create an audio context after getUserMedia is called
            sampleRate might change after getUserMedia is called, like it does on macOS when recording through AirPods
            the sampleRate defaults to the one set in your OS for your playback device
        */
        audioContext = new AudioContext();
        //update the format 
        document.getElementById("formats").innerHTML = "Format: 1 channel pcm @ " + audioContext.sampleRate / 1000 + "kHz"
        /*  assign to gumStream for later use  */
        gumStream = stream;
        /* use the stream */
        input = audioContext.createMediaStreamSource(stream);
        /* 
            Create the Recorder object and configure to record mono sound (1 channel)
            Recording 2 channels  will double the file size
        */
        rec = new Recorder(input, { numChannels: 1 })
        //start the recording process
        rec.record()
        console.log("Recording started");
    }).catch(function (err) {
        //enable the record button if getUserMedia() fails
        recordButton.disabled = false;
        stopButton.disabled = true;
        pauseButton.disabled = true
    });
}
function pauseRecording() {
    console.log("pauseButton clicked rec.recording=", rec.recording);
    if (rec.recording) {
        //pause
        rec.stop();
        pauseButton.innerHTML = "Resume";
    } else {
        //resume
        rec.record()
        pauseButton.innerHTML = "Pause";
    }
}
function stopRecording() {
    console.log("stopButton clicked");
    //disable the stop button, enable the record too allow for new recordings
    stopButton.disabled = true;
    recordButton.disabled = false;
    pauseButton.disabled = true;
    //reset button just in case the recording is stopped while paused
    pauseButton.innerHTML = "Pause";
    //tell the recorder to stop the recording
    rec.stop();
    //stop microphone access
    gumStream.getAudioTracks()[0].stop();
    //create the wav blob and pass it on to createDownloadLink
    rec.exportWAV(createDownloadLink);
}
function createDownloadLink(blob) {
    var url = URL.createObjectURL(blob);
    var au = document.createElement('audio');
    var li = document.createElement('li');
    var link = document.createElement('a');
    //name of .wav file to use during upload and download (without extendion)
    //var filename = new Date().toISOString();
    var filename = new Date().getTime();
    filename = prompt("오디오 파일 제목을 입력하세요.", new Date());
    //add controls to the <audio> element
    au.controls = true;
    au.src = url;
    //save to disk link
    link.href = url;
    link.download = filename + ".wav"; //download forces the browser to donwload the file using the  filename
    link.innerHTML = "Save to disk";
    //add the new audio element to li
    li.appendChild(au);
    //add the filename to the li
    li.appendChild(document.createTextNode(filename + ".wav "))
    //add the save to disk link to li
    li.appendChild(link);
            
    recordingsList.appendChild(li);    
}

$("#uploadFileBtn").click(function() {
	
	$.ajax({
		url:"http://localhost:3000/fileUpload",
		type:"post",
		data:new FormData( $("#upload_file_frm")[0] ),
		enctype: 'multipart/form-data',
		processData:false,
		contentType:false,
		cash:false,
		success:function(msg){
			alert('success');
		//	alert(msg);
		
			let json = JSON.parse(msg);
			$("#msg").text(json.text);		
		},
		error:function(){
			alert('error');
		}		
	});	
});

</script>
</body>
</html>
```

