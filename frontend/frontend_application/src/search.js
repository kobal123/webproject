let request = new XMLHttpRequest();
let request2 = new XMLHttpRequest();


addListener();
addListener2();



function addListener2(){
let input = document.getElementById("searchBar");

let sbutton = document.getElementById("search-button");
sbutton.addEventListener("click",()=>{

window.location = "http://localhost:8080/search?name="+input.value;
});
}

function addListener(){




let input = document.getElementById("searchBar");
let suggestions;
let suggestionsList = document.getElementById("suggestions");




input.addEventListener('input',()=>{
if(input.value.length < 3){
    suggestionsList.innerHTML="";
return;
}

request.onreadystatechange = () =>{
    if(request.readyState === 4){
        suggestions = JSON.parse(request.responseText);
        let i=0;
            suggestions = suggestions.map(element => "<li>"+element+"</li>");
            console.log(i);

        
        console.log(suggestions)
        suggestionsList.innerHTML = suggestions.join("");
        

    }
}
request.open("GET", "http://localhost:8080/api/search/"+input.value);
request.send();

})

}