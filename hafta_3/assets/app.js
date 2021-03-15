let ziyaretciSayisi = 0;

const fonksiyon_adi = (arg1, arg2) => {
    return arg1 * arg2;
};

const yeni_ziyaretci = () => {
    const div = document.getElementById("zyr-say");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) + 1;
    div.textContent = String(yeniRakam);
    
};

const giden_ziyaretci = () => {
    const div = document.querySelector("#zyr-say");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) - 1;
    div.textContent = String(yeniRakam);
};

const ortak_yeni_giden = (rakam) => {
    const div = document.querySelector("#zyr-say");
    const mevcut = div.textContent;
    const yeniRakam = parseInt(mevcut) + rakam;
    div.textContent = String(yeniRakam);
};
