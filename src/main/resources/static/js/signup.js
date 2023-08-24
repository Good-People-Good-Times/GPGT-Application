window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');
    Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
}, false);
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.validation-form');
    const passwordInput = document.getElementById('password');
    const password2Input = document.getElementById('password2');
    form.addEventListener('submit', function(event) {
        if (passwordInput.value !== password2Input.value) {
            alert('비밀번호가 일치하지 않습니다.');
            event.preventDefault();
        }
    });
});