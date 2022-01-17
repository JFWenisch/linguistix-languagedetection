# Linguistix Language Detection 

A lightweight microservice for Language Detection using Apache OpenNLP and Spring Boot. The official Apache OpenNLP Language Detector Model is used and therefore no additional training steps are required.

 
### Language Detector
The [Apache OpenNLP](https://opennlp.apache.org/) library is a machine learning based toolkit for the processing of natural language text. This projectes uses the release of Language Detector Model 1.8.3 for Apache OpenNLP.The Language Detector Model can detect 103 languages and outputs ISO 639-3 codes. This model is trained for and works well with longer texts that have at least 2 sentences or more from the same language.

More information about Language Detector Model can be found under the [https://www.apache.org/dist/opennlp/models/](https://www.apache.org/dist/opennlp/models/langdetect/1.8.3/README.txt). Details about the model effectiveness details can be found in the following [report](https://www.apache.org/dist/opennlp/models/langdetect/1.8.3/langdetect-183.bin.report.txt). 


## Usage
###  Run using docker

You can use the docker image from the github container registry. The application exposes an endpoint on port 8090 which is used to retrieve the input text via a HTTP Post. Therefore we will expose this port when running the image.


```Docker 
docker run -p 8090:8090 ghcr.io/linguistics-ws/linguistix-languagedetection:latest
```


### How to predict the language

```Java
curl --location --request POST 'localhost:8090' \
--header 'Content-Type: application/json' \
--data-raw '{"text": "Das ist nur ein Beispiel. Ich benutze 2 Sätze"}'
```


The endpoint returns an languages array  containing the ISO-639-3 language code and the related confidence. The first object (in this case "deu" with "confidence": 0.04179 ) can be considered the result.
```Java
{
    "languages": [
        {
            "lang": "deu",
            "confidence": 0.041791525520467825
        },
        {
            "lang": "gsw",
            "confidence": 0.02334784919816155
        },
        {
            "lang": "nds",
            "confidence": 0.019932668655377194
        },
        {
            "lang": "ltz",
            "confidence": 0.019517268368118353
        },
        {
            "lang": "oci",
            "confidence": 0.018229406134328816
        },
        {
            "lang": "bre",
            "confidence": 0.01812589897256909
        },
        {
            "lang": "vol",
            "confidence": 0.017797746229008128
        }
    ]
}
```


## Further information

### Own Model
The steps for traning an own model can be found [here](https://opennlp.apache.org/docs/1.8.3/manual/opennlp.html#tools.langdetect). The model is currently packaged with the jar. To use your own model replace the `src/main/resources/langdetect-183.bin` and build the application.

### Build
For manually building the project run
```
mvn clean package
```
### Usage with Eureka Server
The Application can register itself with an Eureka Server instance for service discovery. To do so, set the environment variable `EUREKA_URL`.
### Tools used

 * Java 17
 * Apachen OpenNLP-tools-1.8.4
 * Spring Boot
