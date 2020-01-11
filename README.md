# RoundedProgressBar

A Custom Circular Progress Bar with Rounded Corners

## Installation
Add this in your app's build.gradle file:
```groovy
dependencies {
  implementation 'com.karthi.roundedprogressbar:roundedprogressbar:1.0.6'
}
```

## Usage

```xml
<com.karthi.roundedprogressbar.RoundedCircularProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="160dp"
        android:layout_height="160dp"
        app:rcp_progress="40"
        app:rcp_backgroundColor="@color/colorPrimary"
        app:rcp_progressColor="@color/colorPrimaryDark"
        app:rcp_strokeWidth="16dp" />
```

## Customization and Attributes

All customizable attributes for RoundedProgresssBar
<table>
    <th>Attribute Name</th>
    <th>Default Value</th>
    <th>Description</th>
    <tr>
        <td>app:rcp_progress</td>
        <td>0</td>
        <td>The default progress level</td>
    </tr>
    <tr>
        <td>app:rcp_backgroundColor</td>
        <td>android.R.color.GRAY</td>
        <td>The default progress bar background color</td>
    </tr>
    <tr>
        <td>app:rcp_progressColor</td>
        <td>android.R.color.BLUE</td>
        <td>The default progress color</td>
    </tr>
    <tr>
        <td>app:rcp_strokeWidth</td>
        <td>20px</td>
        <td>The default stroke width</td>
    </tr>
</table>

## Update progress in code
```java
  rcp.serProgress(50);
```

