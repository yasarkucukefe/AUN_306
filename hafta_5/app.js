//const: sabit değer, program akışında yeni bir değer ataması alamaz.
const sabit_deger = 10;
const sabit_string = "portakal";

//let: değişebilen değer. program akışında yeni değerler atanabilir.
let degisecek_deger = 'elma';
degisecek_deger = "armut";

//fonksiyonlar
const fonksiyon_ismi = (arg1, arg2) => {
    //fonksiyon ....
};

const fonksiyon_2 = () => {
    //kod...
};

//tek satırlık yorum

/*
Uzun yorumlar
Satır-1
Satır-2
*/

const topla = (deger1, deger2) => {
    const toplam = deger1 + deger2;
    return toplam;
};

const sonuc = topla(3, 4);
const div = document.querySelector("#sonuc");
div.textContent = sonuc;

// let
const onemli_deger = "adaıoıjasdfı";
let app_deger = "elma";
const baska_bir_fonksiyon = () => {
    app_deger = "armut";
};
baska_bir_fonksiyon();
div.textContent = app_deger;

//Tek satır fonksiyonlar 
const tek_satir_fonksiyon = () => console.log("tek satır fonksiyonlar süslü parantez gerektirmez.");
tek_satir_fonksiyon();
// return olmadan değer döndürülebilir.
const carpma_islemi = (a, b) => a * b;
const carpma_sonucu = carpma_islemi(9, 8);
console.log(carpma_sonucu);

//Javascript Object (nesneleri)
const js_nesne = {isim: "Yaşar", soyisim:"Küçükefe"};
console.log(js_nesne, typeof js_nesne);
console.log(js_nesne.isim);
console.log(js_nesne.soyisim);

//Fonksiyon bir nesne döndürüyorsa parantez kullanılmalıdır.
const tek_satir_nesne_fonk = () => ({isim:"Yaşar", soyisim:"Küçükefe"});

//fonksiyon tek arguman alıyorsa parantezler kullanılmayabilir.
const kare_alan = kenar => kenar * kenar;

//nesnelerde anahtar ve değer aynı ise:
const isim = "Yaşar";
const soyisim = "Küçükefe";
const kisi_nesne = {isim:isim, soyisim:soyisim};
console.log(kisi_nesne);
const yeni_kisi = {isim, soyisim}; // {isim:isim, soyisim:soyisim};

//Operatorler
// +, -, *, **, /, %, ++, --
// https://www.w3schools.com/js/js_operators.asp

let i = 12;
console.log(i++);//12
console.log(i);//13

/*atama operatörleri
=
+= : x=x+4 aynı x+=4
-= : x=x-4 aynı x=x-4    
*/

//Karakter sizinleri (String)
const favori_meyve = "kiraz";
const icecek = "kahve";
const mesaj = favori_meyve + " ve " + icecek + " tercih ederim.";
console.log(mesaj);
//
const mesaj_ = `${favori_meyve} ve ${icecek} tercih ederim.`;
console.log(mesaj_);
//

console.log(5 + "5");//"5"+"5" => 55
console.log(5 - "5"); // 5 - 5 => 0
console.log(5 - "b5"); // 5 - NaN => NaN
console.log(5 - parseInt("b5")); // 5 - NaN => NaN
console.log(5 - "4"); // 5 - 4 => 1

console.log(typeof parseInt("b5"));//??? number ???
//
//JS Operatörleri
//https://www.w3schools.com/js/js_operators.asp
/*
==     :?değer eşit mi? 
===     :?değer ve veri türü eşit mi?
!=      :? değer eşit DEĞİL mi?
!==     :? değer ver veri türü eşit DEĞİL mi?
>       :? büyük mü?   5 > 3 => true,   4 > 6 => false
<       :? küçük mü?
>=      :? büyük veya eşit mi?
<=      :? küçük veya eşit mi?
?       : ternary operatörü => sonuc = (a>b) ? true_ise : false_ise
*/

/*Mantık operatörleri
&&      : VE
||      : VEYA
!       : DEĞİLİ
*/

const a=4,b=3,c="2";
let deger = a == b === c;//false
console.log(deger);

const coklu_deger = () => {
    const isim = "Mahir Çayan", soyisim="Karakaya";
    return {isim, soyisim};
};

const {isim_, soyisim_} = {isim_:"Ahmet",soyisim_:"Mehmet"};//Çoklu değer ataması. Pratik bulmayabilirsiniz.
console.log(isim_,soyisim_);

//Dizinler
const arabalar = ["Tesla","TOGG","Mercedes",123,1]; // === new Array("Tesla", "TOGG", "Mercedes", 123, 1)
arabalar.push("BMW");
console.log(arabalar);
arabalar[3] = "Fiat";
arabalar[4] = "Toyota";
console.log(arabalar);

//length: dizinin kaç tane elemanı olduğunu gösterir
console.log(arabalar.length);
//0 indeksli adresleme:
const ilk = arabalar[0];//ilk eleman
const son = arabalar[arabalar.length-1]; //son eleman

//Döngüler for / while
console.log("For döngüsü");
for(let i=0;i<10;i++){
    console.log(i);//0, 1, 2, 3, 4, 5, 6, 7, 8, 9
}
//continue
console.log("continue ve break");
// continue: döngüdeki takip eden kodu çalıştırmaz, döngü devam eder.
// break: döngüden çıkılır.
for(let i=0;i<10;i++){
    if(i % 2 === 1){continue;}
    if(i>7){break;}
    console.log(i);//0, 1, 2, 3, 4, 5, 6, 7, 8, 9
}

let i_deger=5;
console.log("while");
while(i_deger>0){
    console.log(i_deger--);
}
//
console.log("do while");
do{
    console.log(i_deger++);
}while(i_deger<5);

//for in  (nesneler ve dizinler için döngü)
const ogrenci = {isim:"Oğul Can", soyisim:"Kutay", ogr_no:192032932};
console.log(ogrenci);
for(const anahtar in ogrenci){
    console.log(`${anahtar} => ${ogrenci[anahtar]}`);
}

//for of (dizinler için döngü)
console.log(arabalar);
for(const araba of arabalar){
    console.log(araba);
}

//for in (dizinler için de kullanılabilir.)
for(const idx in arabalar){
    console.log(idx + "=>" +arabalar[idx]);
}

//foreach
console.log("foreach");
const dizin_karakter_say = (kelime) => console.log(`${kelime}=>${kelime.length}`);
arabalar.forEach(dizin_karakter_say);

//switch
const yas = 21;
switch (yas) {
    case 21:
        console.log("21 yaş işlemi");
        break;
    case 15:
        console.log("15 yaş işlemi");
        break;
    default:
        console.log("diğer yaşlar için işlem");    
}

// JSON. Java Script Object Notation
// JSON Object
const nesne_isim = {isim: "Ali", soyisim: "Kurt"};
// JSON dizin
const nesne_dizin = {
    isimler : [
        {isim: "Ali", soyisim: "Kurt"},
        {isim: "Mehmet", soyisim: "Turna"},
        {isim: "Ayşe", soyisim: "Mert"},
    ]
};
console.log(nesne_dizin.isimler);

