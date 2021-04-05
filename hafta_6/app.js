const araba = {
    tip: "Nissan", 
    model:"Juke", 
    renk: "Beyaz",
    arac_bilgileri : function (){
        return `Araç tipi: ${this.tip}, Model:${this.model}, renk:${this.renk}`;
        //return "Araç tipi:"+this.tip+", Model:"+this.model+", renk:"+this.renk;
    },
};

console.log(araba.arac_bilgileri());

const arabalar = ["Volvo", "Mercedes", "TOGG", "Nissan"];
const araclar = arabalar;
console.log(arabalar,araclar);

console.log("--- araclar[0] = Tesla");
araclar[0] = "Tesla";
console.log("Arabalar:",arabalar);

const arac = araba;
console.log(arac,araba);
//
arac.tip = "Mercedes";
arac.model = "C180";
arac.renk = "Kırmızı";
console.log(arac.arac_bilgileri());
console.log(araba.arac_bilgileri());

//SPREAD operator
const araclar_ = [...arabalar]; //... : spread operator
console.log("-- Spread operator --");
console.log(araclar_,arabalar);
araclar_[0] = "BMW";
console.log(araclar_,arabalar);

// Array ve Nesneler için kullanılabilir
const arac_ = {...araba};
console.log(arac_,araba);
arac_.tip = "BMW";
arac_.model = "1.16";
arac_.renk = "Mavi";
console.log(arac_,araba);

/*
//
typeof "Elma"; // "string"
typeof 3.14; // "number"
typeof "4"; // "string"
typeof parseInt("4"); "number"
typeof [1,2,3,4]; // "object"
typeof {isim:"Yaşar", soyisim:"Küçükefe"}; // "object"
typeof NaN; // "number"
typeof null; // "object"

"Elma".constructor // function String()
(3.14).constructor // function Number()
false.constructor // function Boolean()
[1,2,3,4].constructor // function Array()
{isim:"Yaşar", soyisim:"Küçükefe"}.constructor; //function Object()
//
*/
//Örnek: bir değişkenin Array olup olmadığının kontrolü
const array_mi = (degisken) => {
    return degisken.constructor.toString().indexOf("Array") > -1;
};

const is_array = array_mi([1,2,3,4]);
console.log(is_array);
console.log(array_mi("s"));

const array_mi_baska_yontem_ile = (degisken) => {
    return degisken.constructor === Array
}
console.log(array_mi_baska_yontem_ile([3,2,4]))

//
const a = 3;
const b = 4;
const c = "3";
console.log(a == c);
console.log(a === c);

const deger = a == c === false; // false
const deger2 = a == b === false; // true
const deger3 = a == b === (c > 0); //false

// Hata ayıklama

const olmayan_bir_fonksiyon = (deger) => console.log(deger);

try {   
    olmayan_bir_fonksiyon("Fonksiyon tanımlı değil");//Uncaught ReferenceError
}catch(err){
    console.log("Error:",err);
}finally{
    console.log("Her durumda çalışacak kod.")
}

const rakam = 25;
try {
    if(rakam > 20) throw "Rakam 20'den büyüktür."
}catch(err) {
    console.log("Hata:"+err);
}
