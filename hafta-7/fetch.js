
const received_json = (json) => {
	console.log(json);

	document.querySelector("#urun_ad").innerHTML = json.title;
	document.querySelector("#urun_desc").innerHTML = json.description;

	const div_images = document.querySelector("#images");

	json.images.forEach(img_src => {
		const img = `<img src='${img_src}'>`;
		div_images.innerHTML += img;
	})
}


fetch('https://dummyjson.com/products/1')
.then(res => res.json())
.then(json => console.log(json))

