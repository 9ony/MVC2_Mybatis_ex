 /**
 유효성 검사를 위한 자바스크립트 함수 모음
  */
let win=null;
//아이디 중복검사 팝업
function open_idcheck(){
	win=window.open("idcheck.jsp","idCheck","width=400,height=400,left=200,top=200");
}
//회원탈퇴 팝업
function open_delcheck(){
	if(arguments!=undefined){
		var i = arguments[0];
		console.log(i);
		win=window.open("../member/deleteAccount.jsp?idx="+i,"deleteAccount","width=400,height=400,left=200,top=200");
	}else{
		win=window.open("../member/deleteAccount.jsp","deleteAccount","width=400,height=400,left=200,top=200");
	}
}
function del_check(pwd,idx){
	console.log(pwd);
	console.log(typeof(pwd));
	console.log(idx);
	console.log(typeof(idx));
	if(pwd==delf.pwd.value){
		location.href="deleteAccountEnd.jsp?idx="+idx;
		return;
	}else{
		alert("비밀번호가 일치하지 않아요");
		delf.pwd.focus();
		return;
	}
}


function setId(uid){
	//alert(uid);
	//uid값을 부모창(window)의 userid의 value값에 전달하자
	//부모창 (opener ==> window객체)
	//window > document > forms 
	//forms에 이름을 안줬으면 form[0]으로 접근해야함 배열로저장된다!
	opener.document.mf.userid.value=uid;
	
	//팝업창 닫기==>self (window객체)
	self.close();
}
function id_check(){
	if(!idf.userid.value){
		alert('아이디를 입력해야되요!');
		idf.userid.focus();
		return;
	}
	else if(!isUserid(idf.userid)){
		alert('아이디는 영문자,숫자,_,!로 4~8자까지 가능해요');
		idf.userid.select();
		return;
	}
	idf.submit();
}
  
 function member_check(){
/*
	if(!isDate(mf.birth)){
		alert('날짜형식에 맞지 않아요')
		mf.birth.select();
		return;
	}
	if(!isEmail(mf.email)){
		alert('이메일형식에 맞지 않아요')
		mf.email.select();
		return;
	}
*/
	if(!isKor(mf.name)){
		alert('이름은 한글이름만 가능합니다');
		mf.name.select();
		return;
	}
	if(!isUserid(mf.userid)){
		alert('아이디는 영문자,숫자,_,!로 4~8자까지 가능해요');
		mf.userid.select();
		return;
	}
	if(!isPasswd(mf.pwd)){
		alert('비밀번호는 영문자,숫자,!,. 로 4~8자리 까지 가능합니다')
		mf.pwd.select();
		return;
	}
	if(mf.pwd.value!=mf.pwd2.value){
		alert('비밀번호아 비밀번호확인이 달라요!')
		mf.pwd2.select();
		return;
	}
	if(!ismobile(mf.hp1,mf.hp2,mf.hp3)){
		alert('핸드폰 형식에 맞지 않아요')
		mf.hp1.select();
		return;
	}
	mf.submit();
}//-----------------------
/*이메일 실습
	ex)abc@naver.com
	  -ab.c@naver.co.kr 등..
 */
function isEmail(input){
	let val=input.value;
	let pattern=/^[\w-_]+(\.[\w]+)*@([a-zA-Z]+\.)+[a-z]{2,3}$/;
	let b=pattern.test(val);
	alert('email: '+b);
	return b;
}
/**날짜 실습 */
function isDate(input){
	let val=input.value;
	let pattern=/^\d{4}[-\/](0[1-9]|1[012])[-\/](0[1-9]|[12][0-9]|3[01])$/;
	let b=pattern.test(val);
	alert('date: '+b);
	return b;
}
// \w는 알파벳과 숫자만허용
// \. : 마침표
// !
// {4,8} 4~8자리
function isPasswd(input){
	let val=input.value;
	let pattern=/^[\w!\.]{4,8}$/
	let b = pattern.test(val);
	alert('pass :'+b)
	return b;
}
// \b : 단어의 경계를 나타내며, 해당 패턴이 정확하게 일치해야함을 의미
// (010|011) : 010또는 011만 가능
// \d{3,4} : \d는 숫자만 의미하고 {3,4}는 3자리 or 4자리
// \d{4} : 숫자4자리

function ismobile(input1,input2,input3){
	let val = input1.value+"-"+input2.value+"-"+input3.value;
	let pattern=/^\b(010)|(011)[-]\d{3,4}[-]\d{4}\b/;
	alert(val);
	let b = pattern.test(val);
	alert('hp :'+b)
	return b;
}
function isUserid(input){
	let val=input.value;
	//let pattern=/^[abc]+$/; //a or b or c
	let pattern=/^[a-zA-Z0-9_!]{4,8}$/
	let b=pattern.test(val);
	alert('id :'+b);
	return b;
}

/**
^ : 시작을 의미
$ : 끝을 의미
가-힣 : 한글을 의미
+ : 패턴이 한 번 이상 반복된다는 의미 
*/
function isKor(input){
	let val=input.value;
	//let pattern=new RegExp(/multi/g);// multi문자열이 있는지 여부를 체크하는 패턴
	//let pattern=/multi/g
	let pattern=/^[가-힣]+$/;
	let b=pattern.test(val); //정규식 패턴에 맞으면 true를 반환하고, 틀리면 false를 반환한다
	//alert(b);
	return b;
}