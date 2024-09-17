
# Delimiter Separated File Parser

This is a simple web application that allows to upload delimiter separated file (e.g. CSV, TSV) and download it back with specified delimiter.

## How to run

1. Start application with gradle: <br>
`./gradlew bootRun`
2. Open in a browser: http://localhost:8080/v1/dsv

## How to use

1. Go to the page and select file to upload.
2. Click "Upload" button.
3. Select delimiter from the dropdown.
4. Click "Download" button to download the file with selected delimiter.

## Configuration

You can configure the delimiter that is used for the file upload by setting the `delimiter` property in the `application.properties` file.

For example, to set the delimiter to `;` you can add the following line to the `application.properties` file: