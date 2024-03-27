//JSON
// JSON: JavaScript Object Notation

const deger = "1";

//if(deger == 1) // true, == type conversion yaparak karşılaştırır.
//if(deger === 1) // false


const data = {
	"name":"Fatih Bayram",
	"yas": 21,
	"dersler":{
		"kod":"YBS306",
		"ders_ad":"Programalama 2",
		"notlar":[90, 100, 95]		
	}
};

console.log(data); // Konsol çıktısı üretmek için console.log

// JavaScript
const data2 = {
	name: "Öykü Akhisarlı",
	yas: 21,
	dersler: {
		kod:"YBS306",
		ders_ad:"Programlama 2",
		notlar:[100, 100, 100]
	}
}

console.log(data2);

data["okul_no"] = "123918283";
console.log(data);
data["yas"] = 22; //?

// . ile erişim
data.okul_no = "29239939239"; // data.okul_no ile data["okul_no"] eşdeğer.

const data_1 = 1;
const data_2 = 2;
const data_3 = 3;
const abc = {a:data_1, b:data_2, c:data_3};//
console.log(abc);

const x = "X";
const y = 4;
const z = 7823;
const xyz = {x, y, z};// denktir => xyz = {x:x, y:y, z:z}
console.log(xyz);

const xyzd = {x, y, z, d:"Yeni atanan değer"};

const data_12c = {data_1, data_2, data_3}; // {data_1:1, data_2:2, data_3:3}


//soru
const sonuc = xyz.y + xyz.z; // 7827 

//örnek soru
const a1 = "1";
const a2 = 2;
const a3 = a1 + a2; // = "12"
const a4 = parseInt(a1) + a2; // = 3
console.log(a3); // 






















