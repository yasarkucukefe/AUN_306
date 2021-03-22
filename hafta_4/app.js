const renkler_turkce = [
    {
        turkce:'MAVİ',
        class: 'mavi_metin',
        eng: 'Blue'
    },
    {
        turkce:'SARI',
        class:'sari_metin',
        eng:'Yellow'
    },
    {
        turkce: 'YEŞİL',
        class: 'yesil_metin',
        eng:'Green'
    },
    {
        turkce: 'SİYAH',
        class: 'siyah_metin',
        eng:'Black'
    },
    {
        turkce: 'KIRMIZI',
        class:'kirmizi_metin',
        eng:'Red'
    },
    {
        turkce: 'TURUNCU',
        class:'turuncu_metin',
        eng: 'Orange'
    }

];

const div_renk_secildi = (index) => {
    const dil_obj = renkler_turkce[index];
    const renk_ad = document.querySelector('#renk_ad');
    //Türkçe not adını yaz
    renk_ad.textContent = dil_obj.turkce;
    //Uygun renk class'ına değiştir
    renk_ad.className = 'renk_ad '+dil_obj.class;
    //İngilizce Adı
    document.querySelector('#renk_ad_eng').textContent = dil_obj.eng;
};
