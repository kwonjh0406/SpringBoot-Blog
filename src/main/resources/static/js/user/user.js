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

        $.ajax().done().fail() // AJAX 통신을 이용해서 3개의 데이터를 json 형태로 insert 요청
    }
}

index.init()