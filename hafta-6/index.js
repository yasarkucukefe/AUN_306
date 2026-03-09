
// Yorum eklemek için çift eğik çizgi (//) kullanılır. Bu satırda JavaScript kodu yok, sadece bir yorum var.
// alert("Merhaba, JavaScript!");

/*
Çok satırlı yorumlar için bu şekilde kullanılır.
Bu satırda da JavaScript kodu yok, sadece bir yorum var.
*/

const a = 5; // Değişken tanımlama ve atama, const: bu değer değiştirilemez (constant).
// a = 6; // Hata verir. const ile atanan değerler değiştirilemez.

let b = 6; // Değişken tanımlama ve atama, let: bu değer değiştirilebilir
b = 9; // let ile tanımlandığı için yeni bir değer atanabilir.

let z = a * b + 2;

b++; // b = b + 1
b += 1; // b = b + 1;
console.log(b); 

b--; // b = b - 1
b -= 1; // b = b - 1

b = 2; // integer
const d = "1"; // String çift tırnak veya tek tırnak içerisinde değer

const e = b + d;
console.log(e);// 21

const f = "2"; // String
// == ile if kontrolü
if(f == b) { // b=2, f="2"
    console.log("f==b : True"); // Output
}else{
    console.log("f==b : False")
}

// === ile if kontrolü
if(f === b) { // b=2, f="2"
    console.log("f===b : True"); 
}else{
    console.log("f===b : False") // Output
}

// 
let g = f == b && f === b; // False
g = f == b || f === b; // True

// a = 5
if (a > 3){
    console.log("a > 3"); // output. if blokundan çıkar, takip eden True koşullar çalışmaz.
} else if (a > 4){
    console.log("a > 4");
} else if (a > 5){
    console.log("a > 5");
} else {
    console.log("else");
}

// Ternary operator
const yas = 21;
let mesaj = (yas > 18) ? "Reşit" : "Reşit Değil"; // (True-False Kontrol) ? "True ise değer" : "False ise değer"

// Indirim oranı hesapla, üyeler için %10, üye olmayanlar için %5
const uyeMi = true;
let indirimOran = 0;
if (uyeMi){
    indirimOran = 0.10;
} else {
    indirimOran = 0.05;
}

// Ternary operator ile:
indirimOran = uyeMi ? 0.10 : 0.05;

// Switch
// haftanın hangi günü
const hangiGun = new Date().getDay(); // Pazar=0, Pazartesi=1, ..., Cumartesi:6
switch(hangiGun){
    case 1: // Pazartesi
        indirimOran = 0.20;
        break;
    case 3: // Çarşamba
    case 4: // Perşembe
        indirimOran = 0.15;
        break;
    default:
        indirimOran = 0.05;
}

console.log(`İndirim oranı: ${indirimOran}`);

// Loops - Döngüler
// for loop
console.log("For loop")

for(let i=1; i <= 10; i++){
    console.log(i);
}

for(let i=1; i <= 10; i++){
    if (i == 4){ continue; } // i=4 olduğunda for bloğundaki takip eden kodu atla ama for döngüsüne devam et
    if (i > 8){ break;} // i>8 olduğunda for döngüsünden çık
    console.log(i);
}

// while
let i = 0;
console.log("While loop")
while (i < 10){
    i++;
    if (i == 3){ continue; } // i=4 olduğunda for bloğundaki takip eden kodu atla ama for döngüsüne devam et
    if (i > 7){ break;} // i>8 olduğunda for döngüsünden çık
    console.log(i);
}


// do while
i = 11; // !: do-while kontrol koşulu FALSE
// do-while ilk döngüyü mutlaka çalıştırır, koşulu kontrolü FALSE olsa bile.
console.log("do-While loop")
do {
    i++;
    if (i == 13){ continue; } // i=4 olduğunda for bloğundaki takip eden kodu atla ama for döngüsüne devam et
    if (i > 17){ break;} // i>8 olduğunda for döngüsünden çık
    console.log(i);
} while (i < 10)


// forEach loop
const sayilar = [1, 2, 3, 4, 5]; // Array oluşturma
console.log("forEach loop")
sayilar.forEach(sayi => {
    //if(sayi == 3) {continue;} // forEach döngüsünde continue kullanılamaz, hata verir.
    // if(sayi > 4) {break;} // forEach döngüsünde break kullanılamaz, hata verir.
    console.log(sayi);
});

let sonuc = topla(3, 4); // hata vermez
console.log("Sonuc="+sonuc);

// Fonksiyonlar
function topla(x, y){
    return x + y;
}

// sonuc = carpma(3, 4); // hata verir, çünkü fonksiyon tanımlanmadan önce çağrıldı. ok fonksiyonları (arrow functions) hoisting'e tabi değildir, yani tanımlanmadan önce kullanılamazlar.

const carpma = (x, y) => {
    return x * y;
}

sonuc = carpma(5,6);
console.log(`Sonuc=${sonuc}`);

let ax = 1;
const bx = 3;

const islemYap = (c) => {
    const sonuc = c * ax + bx;
    return sonuc;
}

sonuc = islemYap(4);
console.log(`Sonuc=${sonuc}`);

console.log("ax="+ax);
const islemYap2 = (c) => {
    ax = 8;
    const sonuc = c * ax + bx;
    return sonuc;
}

sonuc = islemYap2(5);
console.log(`Sonuc=${sonuc}`);
console.log("ax="+ax);
// Bir fonksiyon içerisinden fonksiyon dışında declare edilmiş bir değişken değeri kullanılabilir ve değiştirilebilir.


// Array 
const myArray = ["Elma","Muz","Portakal"];
myArray.push("Nar"); // yeni bir eleman ekle
console.log(myArray.length + " tane eleman içerir"); 
const sonEleman = myArray.pop(); // son elemanı çıkarır ve döndürür
console.log("Çıkarılan eleman: " + sonEleman);
console.log(myArray); // ["Elma","Muz","Portakal"]
myArray.unshift("Çilek"); // başa yeni bir eleman ekle
console.log(myArray); // ["Çilek","Elma","Muz","Portakal"]
const ilkEleman = myArray.shift(); // baştaki elemanı çıkarır ve döndürür
console.log("Çıkarılan eleman: " + ilkEleman);
console.log(myArray); // ["Elma","Muz","Portakal"]

// Array elemanlarına erişim (sıfır indexten başlar)
console.log(myArray[0]); // Elma
console.log(myArray[1]); // Muz
console.log(myArray[2]); // Portakal
// console.log(myArray[3]); // undefined, çünkü 3. indexte bir eleman yok

if(myArray.includes("Muz")){
    console.log("Muz var");
} else {
    console.log("Muz yok");
}

// indexOf: elemanın indexini döndürür, eğer eleman yoksa -1 döndürür
if(myArray.indexOf("Portakal") !== -1){
    console.log("Portakal var");
} else {
    console.log("Portakal yok");
}

console.log(`Üzüm elemanının indexi: ${myArray.indexOf("Üzüm")}`); // -1, çünkü Üzüm elemanı yok

// myArray = []; // Hata verir.
myArray.length = 0; // Array'i boşaltır
console.log(myArray); // []


myArray2 = myArray; // myArray2, myArray'in referansını tutar, yani aynı array'e işaret ederler
myArray2.push("Karpuz");
console.log(myArray); // ["Karpuz"]
console.log(myArray2); // ["Karpuz"]
// myArray ve myArray2 aynı array'e işaret ederler, bu yüzden birinde yapılan değişiklik diğerini de etkiler. Bu durum referans tipi veri yapılarında görülür.

myArray.push("Ananas");
console.log(myArray); // ["Karpuz", "Ananas"]
console.log(myArray2); // ["Karpuz", "Ananas"]

// Array'i kopyalamak için spread operator kullanabiliriz
const myArray3 = [...myArray]; // myArray3, myArray'in bir kopyasını tutar, yani farklı bir array'e işaret ederler
myArray3.push("Mango");
console.log(myArray); // ["Karpuz", "Ananas"]
console.log(myArray3); // ["Karpuz", "Ananas", "Mango"]
// myArray ve myArray3 farklı array'lere işaret ederler, bu yüzden birinde yapılan değişiklik diğerini etkilemez.

// JSON: JavaScript Object Notation
const person = {
    name: "Yasarkucukefe",
    age: 51,
    city: "Ankara"
};

// JSON.stringify: JavaScript objesini JSON string'ine çevirir
const personJSON = JSON.stringify(person);
console.log(personJSON); // {"name":"Yasarkucukefe","age":21,"city":"Ankara"}

// JSON.parse: JSON string'ini JavaScript objesine çevirir
const personObj = JSON.parse(personJSON);
console.log(personObj); // {name: "Yasarkucukefe", age: 21, city: "Ankara"}


// JSON anahtar değer ataması
person.name = "Ahmet";
person["age"] = 43;
console.log(person);

const x = 3;
const y = 5;
const z1 = "abc";
const xyz1 = {x, y, z1}; // {x:3, y:5, z1:"abc"}


function islemYapJSON(c){
    const c1 = c*12;
    const c2 = c-21;
    const c3 = "xyzC"+c;
    return {c1, c2, c3};
}

const {c1, c2, c3} = islemYapJSON(3);
console.log(c1,c2,c3);

