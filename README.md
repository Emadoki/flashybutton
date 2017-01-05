# flashybutton
## Custom animations for android button (Beta)

![](https://github.com/Emadoki/flashybutton/raw/master/flashybutton.gif)

Just a basic and noob library for learning purposes

#### Import project
Add this to your root gradle
```java
allprojects {
    repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

Add this to your app gradle
```java
compile 'com.github.Emadoki:flashybutton:1.0.0'
```

#### XML
```xml
app:animation="firework"
app:checked="false"
app:iconOff="@drawable/ic_like_off"
app:iconOn="@drawable/ic_like_on"
app:primaryColor="#3F51B5"
app:secondaryColor="#673AB7"
```

### JAVA
```java
((FlashyButton) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View v)
    {
        ((FlashyButton) v).play();
    }
});
```

### Inspiration
[https://github.com/varunest/SparkButton](https://github.com/varunest/SparkButton)

### License
```java
Copyright 2017 Emadoki

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```