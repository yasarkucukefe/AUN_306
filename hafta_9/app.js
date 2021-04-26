$(document).ready(function(){
    console.log("Jquery yüklendi ve DOM hazır.");//DOM: Document Object Model
    //
    $(".buton").click(function(){
        const $buton = $(this);$buton.css("opacity","0.5");
        setTimeout(()=>{$buton.css("opacity","1");},300);
    });
    //
    $(".esit").click(function(){
        const $buton = $(this);$buton.addClass("buton_animasyon");
        setTimeout(()=>{$buton.removeClass("buton_animasyon");},300);
    });
    //
    $("select").change(function(){
        const tema = $(this).val();
        tema_degistir(tema);
    });

});

const tema_degistir = (tema) => {
    if(parseInt(tema) === 1){$("#section-hesap").removeClass("koyu_renk");$(".sonuc").remove("koyu_sonuc");}
    else if (parseInt(tema) === 2){$("#section-hesap").addClass("koyu_renk");$(".sonuc").addClass("koyu_sonuc");}
};
