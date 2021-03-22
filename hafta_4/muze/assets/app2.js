
const aktif_ziyaretci_say = localStorage.getItem('aktif_ziyaretci') || '0';
const toplam_ziyaretci_say = localStorage.getItem('toplam_ziyaretci') || '0';
document.querySelector("#zyr-say").textContent = aktif_ziyaretci_say;
document.querySelector("#toplam-zyr").textContent = toplam_ziyaretci_say;

const sifirla_ziyaretci_say = () => {
    localStorage.setItem('aktif_ziyaretci',"0");
    localStorage.setItem('toplam_ziyaretci',"0");
    document.querySelector("#zyr-say").textContent = "0";
    document.querySelector("#toplam-zyr").textContent = "0";
};

const ortak_yeni_giden = (rakam) => {
    const div = document.querySelector("#zyr-say");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) + rakam;
    // Günlük limit
    const limitInput = document.querySelector("#zyr-limit");
    const limit = parseInt(limitInput.value);

//eğer yeni rakam 0'dan küçük ise 0'da kalsın.
    const uyari = document.querySelector(".uyari");
    
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
        localStorage.setItem('aktif_ziyaretci',yeniRakam);
        if(rakam === 1){toplam_ziyaretci();}
    }    
};

const toplam_ziyaretci = () => {
    const div = document.querySelector("#toplam-zyr");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) + 1;
    div.textContent = yeniRakam;
    localStorage.setItem('toplam_ziyaretci',yeniRakam);
};

const btn_reset = document.querySelector("#reset");
btn_reset.addEventListener("click",sifirla_ziyaretci_say);

const inp_yeni = document.querySelector("#inp_yeni_zyr");
inp_yeni.addEventListener("click",() => ortak_yeni_giden(1));

const inp_giden = document.querySelector("#inp_giden_zyr");
inp_giden.addEventListener("click",() => ortak_yeni_giden(-1));
