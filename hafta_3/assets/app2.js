let ziyaretciSayisi = 0;

const ortak_yeni_giden = (rakam) => {
    const div = document.querySelector("#zyr-say");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) + rakam;
    // Günlük limit
    const limitInput = document.querySelector("#zyr-limit");
    const limit = parseInt(limitInput.value);

//eğer yeni rakam 0'dan küçük ise 0'da kalsın.
    const uyari = document.querySelector(".uyari");
    if(rakam === 1){toplam_ziyaretci();}
    if(yeniRakam < 0) {
        div.textContent = "0";
        uyari.textContent = "Ziyaretçi sayısı sıfırdan küçük olamaz.";
    }else if(yeniRakam > limit){
        div.textContent = String(limit);
        //uyari.textContent = "Ziyaretçi sayısı " + limit + "'dan büyük olamaz.";
        uyari.textContent = `Ziyaretçi sayısı ${limit}'dan büyük olamaz`;
    }else{
        uyari.textContent = "";
        div.textContent = String(yeniRakam);
    }
};

const toplam_ziyaretci = () => {
    const div = document.querySelector("#toplam-zyr");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) + 1;
    div.textContent = yeniRakam;
};
