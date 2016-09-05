
<h1 align="center">JitPack [![](https://jitpack.io/v/xiaobailong24/JitPack.svg)](https://jitpack.io/#xiaobailong24/JitPack)

# v1.0:
1. CircleProgress: https://jitpack.io/#xiaobailong24/JitPack/v1.0

# Quick Look

![GIF](/gif/GIF.gif)

# How to include

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.xiaobailong24:JitPack:v1.0'
	}

# Usage
```
<me.xiaobailong24.library.View.CircleProgress
    android:id="@+id/progress"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    circleprogress:color1="@android:color/holo_red_light"
    circleprogress:color2="@android:color/holo_green_light"
    circleprogress:color3="@android:color/holo_blue_light"/>
```



```
mProgressView.startAnim();

mProgressView.stopAnim();

mProgressView.reset();

mProgressView.setDuration();

mProgressView.setInterpolator();
```

