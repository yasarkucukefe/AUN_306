
//Jquery sağlıyor
$(document).ready(function(){
    $("p").click(function(){
        const p_metin = $(this).text();
        console.log(p_metin);
        $("h1").text(p_metin);
    });

    $("h1").mouseenter(function(){
        $(this).addClass("mouseEnter");
    });

    $("h1").mouseleave(function(){
        $(this).removeClass("mouseEnter");
    });

    $("input[name=filtre]").keyup(function(e){
        //console.log(e.key);
        const filtre_metin = $(this).val();
        //console.log(filtre_metin);
        fonksiyonTabloVerileri(filtre_metin);
    });

    $("tbody").on("click","tr",function(){
        $("tr").removeClass("secili");
        $(this).addClass("secili");
        const ulke = $(this).find("td:first").text();
        const baskent = $(this).find("td:eq(1)").text();
        console.log(ulke,baskent);
        $("h1").text(`Seçilen ülke: ${ulke} => ${baskent}`);
    });
});

const fonksiyonTabloVerileri = (filtre) => {
    const $tr = $("tbody tr");
    $.each($tr,function(index,key){//$.each <= Jquery
        const ulke = $(this).find("td:first").text();//Ülke
        const baskent = $(this).find("td:eq(1)").text();//Başkent

        //if(ulke.includes(filtre)){console.log("göster "+ulke);}else{console.log("sakla "+ulke);}
        //if(baskent.includes(filtre)){console.log("göster "+baskent);}else{console.log("sakla "+baskent);}

        if(ulke.includes(filtre) || baskent.includes(filtre)){
            console.log("göster => ",ulke,baskent);
            $(this).removeClass("sakla");
        }else{
            console.log("sakla => ",ulke,baskent);
            $(this).addClass("sakla");
        }

    });
};

console.log("Doküman yükleniyor...");
