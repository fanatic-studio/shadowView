## 安装

```shell script
eeui plugin install eeui/shadowView
```

## 卸载

```shell script
eeui plugin uninstall eeui/shadowView
```

## 组件

```html
<shadow-view>...</shadow-view>
```

## 参数

* `placement` 显示阴影位置
* `blur` 阴影扩散尺寸
* `offsetX` X轴偏移尺寸
* `offsetY` Y轴偏移尺寸
* `color` 阴影颜色
* `backgroundColor` 背景颜色

## 示例

```html
<template>
    <scroller class="app">
        
        <shadow-view
                class="shadow"
                placement="all"
                blur="30"
                offsetX="0"
                offsetY="0"
                color="#ff0000"
                backgroundColor="#ffffff">
            <text class="text">11121112111211121112111234567890</text>
            <text>222</text>
            <text>333</text>
        </shadow-view>
        
        <shadow-view
                class="shadow"
                placement="top|left"
                blur="30"
                offsetX="0"
                offsetY="0"
                color="#ff0000"
                backgroundColor="#ffffff">
            <text class="text">11121112111211121112111234567890</text>
            <text>222</text>
            <text>333</text>
        </shadow-view>
        
        <shadow-view
                class="shadow"
                placement="right|bottom"
                blur="30"
                offsetX="0"
                offsetY="0"
                color="#ff0000"
                backgroundColor="#ffffff">
            <text class="text">11121112111211121112111234567890</text>
            <text>222</text>
            <text>333</text>
        </shadow-view>
        
    </scroller>
</template>
<style scoped>
    .app {
        flex: 1;
    }
    .shadow {
        width: 300px;
        height: 300px;
        margin: 80px 225px;
        padding: 50px
    }
    .text {
        font-size: 32px
    }
</style>
<script>
    export default {}
</script>
```

## 效果

![image](https://console.eeui.app/uploads/picture/1/202007/ad5bfa9ae78eb84486dbc94b6aeb7714.png)
![image](https://console.eeui.app/uploads/picture/1/202007/8ded5362266e212e171721a92bdb7496.png)

## 注意

* 因为Android和iOS机制不同导致两端的效果有所差别，希望大神可以解决。