function convertToGS(img) {
    if (!Modernizr.canvas) return;

    //存储原始的彩色版
    img.color = img.src;

    //创建灰度版
    img.grayscale = createGSCanvas(img);

    //在mouseover/out发生时切换图片
    img.onmouseover = function () {
        this.src = this.color;
    }
    img.onmouseout = function () {
        this.src = this.grayscale;
    }

    img.onmouseout;
}

function createGSCanvas(img) {
    let canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;

    let ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0);

    //getImageData只能操作位于同一个域中的图片
    let c = ctx.getImageData(0, 0, img.width, img.height);
    for (let i = 0; i < c.height; i++) {
        for (let j = 0;i < c.width; j++){
            let x = (i*4)*c.width+(j*4);
            let r = c.data[x];
            let g = c.data[x+1];
            let b = c.data[x+2];
            c.data[x] = c.data[x+1] = c.data[x+2] = (r+g+b)/3;
        }

        ctx.putImageData(c,0,0,0,0,c.width,c.height);
        return canvas.toDataURL();

    }
}
let ava = document.getElementById("avatar");
addLoadEvent(convertToGS(ava));