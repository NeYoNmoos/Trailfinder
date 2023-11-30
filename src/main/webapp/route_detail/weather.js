const latitude = 37.7749; // Replace with your actual latitude
const longitude = -122.4194; // Replace with your actual longitude

// Rest of your JavaScript code here
const wrapper = document.querySelector(".wrapper");
locationBtn = wrapper.querySelector(".input-part button");
wIcon = document.querySelector("img");
infoTxt = wrapper.querySelector(".input-part .info-txt");

// Function to handle API request
function requestApi(latitude, longitude) {
    const api = `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&units=metric&appid=8641075dda9ea5d5c961c48c00929bec`;
    fetchData(api);
}

// Function to handle API fetch
function fetchData(api) {
    infoTxt.innerText = "Getting weather details...";
    infoTxt.classList.add("pending");

    fetch(api)
        .then(response => response.json())
        .then(result => weatherDetails(result))
        .catch(error => {
            infoTxt.classList.replace("pending", "error");
            infoTxt.innerText = "Error fetching weather data.";
        });
}


function weatherDetails(info){
    if(info.cod == "404"){
        infoTxt.classList.replace("pending", "error");
        infoTxt.innerText = `${inputField.value} isn't a valid city name`;
    }else {
        //console'dan alınan bilgiler
        const city = info.name;
        const country = info.sys.country;
        const {description, id} = info.weather[0];
        const {feels_like, humidity, temp} = info.main;

        if(id == 800){
            wIcon.src="assets/clear.svg"
        } else if (id >= 200 && id <=232){
            wIcon.src="assets/strom.svg"
        } else if (id >= 600 && id <= 622){
            wIcon.src="assets/snowy.svg"
        } else if (id >= 701 && id <=781){
            wIcon.src="assets/haze.svg"
        } else if (id >= 801 && id <=804){
            wIcon.src="assets/cloudy.svg"
        }  else if ((id >= 300 && id <= 321) || (id >= 500 && id <= 531)){
            wIcon.src="assets/rainy.svg"
        }

        //html'e basmamız için
        wrapper.querySelector(".temp .numb").innerText =  Math.floor(temp);
        wrapper.querySelector(".weather").innerText = description.toUpperCase();
        wrapper.querySelector(".location span").innerText =` ${city},${country}`;
        wrapper.querySelector(".temp .numb-2").innerText = Math.floor(feels_like);
        wrapper.querySelector(".humidity span").innerText=`${humidity}%`;

        infoTxt.classList.remove("pending", "error");
        infoTxt.innerText = "";
        inputField.value ="";
        wrapper.classList.add("active"); // hava durumunu gösterir.
    }
};


// Initial request with constants
requestApi(latitude, longitude);