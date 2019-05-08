const http = new XMLHttpRequest();
const url = "http://localhost:8080/acronyms?bullets=";

apiCall = function(bullets) {
    http.open("GET", url + bullets);
    http.send();
    http.send();
    console.log("end of api call");
};

http.onreadystatechange = function(e){

    let responseObject = JSON.parse(http.responseText);
    let numAcronyms = responseObject.acronyms.length;
    let textOutput = "";

    

    for (i=0;i<numAcronyms;i++) {
        let tempDef = responseObject.acronyms[i].definition;
        let tempName = responseObject.acronyms[i].name;
        textOutput += tempDef + " (" + tempName + "); ";
    };
    textOutput=textOutput.substring(0,textOutput.length-2);

    $("#outputText").val(textOutput);
    console.log(http.responseText);
}

console.log("loaded");
