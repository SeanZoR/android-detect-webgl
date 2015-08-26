Android Detect WebGL
===============

[![Build Status](https://travis-ci.org/SeanZoR/android-detect-webgl.svg)](https://travis-ci.org/SeanZoR/android-detect-webgl)

Android Library to easily allow you to determine device's WebGL support level

## Download
```groovy
dependencies {
  compile 'com.github.seanzor.webgl.detect:library:1.0.1'
}
```

## Usage
As easy as:
```java
// Trigger the check
WebGLDetector.detect(this, new OnReceiveDetectJsResult() {
    @Override
    public void onReceiveDetectJsResult(WebGLSupportLevel supportLevel) {
        switch (supportLevel){
            case UNKNOWN:
                break;
            case NOT_SUPPORTED:
                break;
            case SUPPORTED_BUT_DISABLED:
                break;
            case SUPPORTED:
                break;
        }
    }
});
```


## License

    Copyright 2015 Sean Katz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
