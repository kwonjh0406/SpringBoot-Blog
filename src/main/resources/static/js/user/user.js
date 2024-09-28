let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(), password: $("#password").val(), email: $("#email").val()

        }

        // ajax 호출시 default가 비동기 호출
        $.ajax({
            // 회원가입 수행 요청 - 응답 결과에 따라 done or fail
            type:"POST",
            url:"/api/user",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지 내가 보내는 타입
            dataType:"json" // 응답을 받을 타입
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }) // AJAX 통신을 이용해서 3개의 데이터를 json 형태로 insert 요청
    }
}

index.init()