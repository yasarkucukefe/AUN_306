//alert("Javascript index.js dosyasındaki JS alert satırından Merhaba");

const modulus_operator = (a,b) => {
	//Modulus operatorü (%) a'nın b'ye bölümünden kalanı verir.
	const kalan = a % b;
	return kalan
}

// const ile declare edilmis aynı isimde birden farklı fonksiyon olamaz

const while_loop = () => {	
	let rakam = 1;
	while(rakam < 11){
		document.getElementById("rakamlar-js").innerHTML = document.getElementById("rakamlar-js").innerHTML + rakam +", " ;
		rakam++;
	}	
}


// JS Arrays
const liste = [1,2,3,4,5]; // Array

//Quiz: Elemanları 20 ve 30 arasında (20 ve 30 dahil) tam sayılar olan "arr" adında bir array oluşturunuz.

const arr = [];
arr.push(1); // arr = [1];

const for_loop = () => {	
	const arr = [];
	for(let i=1; i<101; i++){
		arr.push(i);
	}
	document.getElementById("rakamlar2-js").innerHTML = arr.join(", ");	
}

//Array'ler adres bazlı olarak saklanırlar.
const a = [5,6,7];
const b = a;

console.log(arr[0]); //Arrayin ilk elemanı
console.log(arr.length-1); //Arrayin son elemanı

console.log(a,b)

b.push(8); // a array'ine de 8 eklenir.
console.log(a,b)

a.pop(); //Array'in son elemenı çıkarılır.

a.shift(); // Array'in ilk elemanını çıkarır.

a.length; // Array'in kaç elemandan oluştuğunu return eder.

a.unshift(1); // Array'in başına yeni bir eleman ekler (1)


const c = [...a]; // a array'inin elemanları c'ye koplayalanır ama a ve c farklı adreslerde saklandıkları için değişiklikleri diğerini etkilemez (Spread operator)


const my_arr = [3,5,6,7,8];

my_arr.forEach(elm => {
	console.log(elm);
})

my_arr.push([2,4,'elma']); //my_arr = [3,5,6,7,8,[2,4,'elma']]

//
const fruits = ["Banana", "Orange", "Apple", "Mango"];
fruits.reverse(); // fruits = ['Mango', 'Apple', 'Orange', 'Banana'], orjinal array değişir.

fruits.sort(); //alfatik olarak sıralanır
console.log(fruits); //orjinal array değişir


// Template strings
const isim = "Mehmet";
const yas = 25;

const cumle = isim + " " + yas + " yaşındadır.";
console.log(cumle);

const cumle2 = `${isim} ${yas} yaşındadır.`;
console.log(cumle2);