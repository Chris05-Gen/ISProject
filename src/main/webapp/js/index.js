document.addEventListener("DOMContentLoaded", () => {
    console.log("index.js caricato");

    // 🔍 Elementi comuni
    const searchForm = document.querySelector('.search-form');
    const header2 = document.querySelector('.header .header-2');
    const menuBtn = document.querySelector('#menu-btn');
    const navbar = document.querySelector('.navbar');
    const searchBtn = document.querySelector('#search-btn');
    const loginBtn = document.querySelector('#login-btn');
    const closeLoginBtn = document.querySelector('#close-login-btn');
    const userBtn = document.getElementById("user-btn");
    const userForm = document.getElementById("userForm");
    const closeBtn = document.getElementById("close-form");
    const closeToast = document.querySelector(".close-toast");

    const loginFormContainer = document.getElementById("login-form");
    const registerFormContainer = document.getElementById("register-form");
    const showRegisterLink = document.getElementById("show-register");
    const showLoginLink = document.getElementById("show-login");
    const openLoginFromCart = document.getElementById("open-login-from-cart");

    // 🍔 Menu toggle
    if (menuBtn && navbar) {
        menuBtn.addEventListener('click', () => {
            navbar.classList.toggle('show');
        });
    }

    // 🔍 Search toggle
    if (searchBtn && searchForm) {
        searchBtn.onclick = () => {
            searchForm.classList.toggle('active');
        };
    }

    // 🔐 Login form toggle
    if (loginBtn && userForm) {
        loginBtn.onclick = () => userForm.classList.toggle('active');
    }
    if (closeLoginBtn && userForm) {
        closeLoginBtn.onclick = () => userForm.classList.remove('active');
    }

    // 👤 User form popup
    if (userBtn && userForm) {
        userBtn.onclick = () => {
            userForm.classList.add("active");
            loginFormContainer.style.display = "block";
            registerFormContainer.style.display = "none";
        };
    }
    if (closeBtn && userForm) {
        closeBtn.onclick = () => userForm.classList.remove("active");
    }

    // 🔄 Toggle Login/Registrazione
    if (showRegisterLink && showLoginLink && loginFormContainer && registerFormContainer) {
        showRegisterLink.addEventListener("click", function (e) {
            e.preventDefault();
            loginFormContainer.style.display = "none";
            registerFormContainer.style.display = "block";
        });

        showLoginLink.addEventListener("click", function (e) {
            e.preventDefault();
            registerFormContainer.style.display = "none";
            loginFormContainer.style.display = "block";
        });
    }

    // 🚪 Apertura login da carrello
    if (openLoginFromCart && userForm && loginFormContainer && registerFormContainer) {
        openLoginFromCart.addEventListener("click", function (e) {
            e.preventDefault();
            userForm.classList.add("active");
            loginFormContainer.style.display = "block";
            registerFormContainer.style.display = "none";
        });
    }

    // 🔕 Toast
    if (closeToast) {
        closeToast.addEventListener("click", () => {
            closeToast.parentElement.style.display = "none";
        });
    }
    setTimeout(() => {
        const toast = document.querySelector('.toast');
        if (toast) {
            toast.style.display = 'none';
        }
    }, 5500); // 5.5 secondi

    // 📜 Scroll handling
    const handleScroll = () => {
        if (searchForm) searchForm.classList.remove('active');
        if (window.scrollY > 93) {
            header2?.classList.add('active');
        } else {
            header2?.classList.remove('active');
        }
    };
    window.onscroll = handleScroll;
    handleScroll(); // al load

    // 💨 Loader
    const loader = () => {
        document.querySelector('.loader-container')?.classList.add('active');
    };
    const fadeOut = () => setTimeout(loader, 4000);
    fadeOut();

    // 🌀 Swiper init
    const swiperContainer = document.querySelector('.featured-slider');
    if (typeof Swiper !== 'undefined' && swiperContainer) {
        const swiper = new Swiper('.featured-slider', {
            loop: true,
            spaceBetween: 20,
            autoplay: {
                delay: 4500,
                disableOnInteraction: false,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            slidesPerGroup: 1,
            breakpoints: {
                0: { slidesPerView: 1 },
                768: { slidesPerView: 2 },
                1024: {
                    slidesPerView: 3,
                    slidesPerGroup: 1,
                },
            },
        });

        // Stop autoplay on hover
        const slides = document.querySelectorAll('.featured-slider .swiper-slide');
        slides.forEach(slide => {
            slide.addEventListener('mouseenter', () => swiper.autoplay.stop());
            slide.addEventListener('mouseleave', () => swiper.autoplay.start());
        });
    } else {
        console.log("🟡 Swiper non inizializzato: non richiesto in questa pagina");
    }

        const btn = document.getElementById("btn-aggiungi-indirizzo");
        const form = document.getElementById("form-aggiungi-indirizzo");
        console.log("btn:", btn, "form:", form);

        if (btn && form) {
            btn.addEventListener("click", () => {
                form.style.display = "block";
                btn.style.display = "none";
            });
        } else {
            console.warn("❌ Bottoni/form non trovati nel DOM!");
        }
    const btnAggiungi = document.getElementById("btn-aggiungi-indirizzo");
    const btnAnnulla = document.getElementById("btn-annulla-indirizzo");
    const formAggiunta = document.getElementById("form-aggiungi-indirizzo");

    if (btnAggiungi && formAggiunta) {
        btnAggiungi.addEventListener("click", () => {
            formAggiunta.style.display = "block";
            btnAggiungi.style.display = "none";
        });
    }

    if (btnAnnulla && formAggiunta) {
        btnAnnulla.addEventListener("click", () => {
            formAggiunta.style.display = "none";
            btnAggiungi.style.display = "block";
        });
    }
});
