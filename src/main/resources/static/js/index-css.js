
//año
//{
    date = new Date();
    year = date.getFullYear();
    month = date.getMonth() + 1;
    day = date.getDate();
    document.getElementById("current_date").innerHTML = year;
//}

var nav = document.querySelector('nav');
window.addEventListener('scroll', function()
{
    if (window.pageYOffset > 700)
    {
        nav.classList.add("bg-info", "shadow");
    }
    else
    {
        nav.classList.remove("bg-info", "shadow");
    }
});
