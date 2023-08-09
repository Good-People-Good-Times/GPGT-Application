const toggleBtn = document.querySelector('.navbar_toggleBtn');
const menu = document.querySelector('.navbar_menu');
const my = document.querySelector('.navbar_my');

toggleBtn.addEventListener('click', ()=> {
    menu.classList.toggle('active');
    my.classList.toggle('active');
});