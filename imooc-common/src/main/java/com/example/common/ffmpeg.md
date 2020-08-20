```$xslt
横向合并视频
ffmpeg -i input1.mp4 -i input2.mp4 -lavfi hstack output.mp4

上面的命令虽然可以合并视频，两个视频可以正常播放，但是只保留了前面一个的音频。
下面会介绍怎么避开这个坑。

注意这时候input1和input2必须同样的高度，如果不一样的高度可以使用-shortest参数来保证同样的高度。

如果希望合并多个视频，可以使用下面命令行。
ffmpeg -i input1.mp4 -i input2.mp4 -i input3.mp4 -lavfi hstack=inputs=3 output.mp4
其中input=3表示希望合并的视频的个数

纵向合并视频
ffmpeg -i input1.mp4 -i input2.mp4 -lavfi vstack output.mp4

网格合并视频
当多个视频时，还可以合并成网格状，比如2x2，3x3这种。但是视频个数不一定需要是偶数，如果是奇数，可以用黑色图片来占位。

ffmpeg -f lavfi -i color=c=black:s=1280x720 -vframes 1 black.png
该命令将创建一张1280*720的图片

然后就可以使用下面这个命令来合并成网格视频了，如果只有三个视频，可以选择上面创建的黑色图片替代。
ffmpeg -i top_left.mp4 -i top_right.mp4 -i bottom_left.mp4 -i bottom_right.mp4 \
-lavfi "[0:v][1:v]hstack[top];[2:v][3:v]hstack[bottom];[top][bottom]vstack"
-shortest 2by2grid.mp4

上面创建的是正规的2x2网格视频。想象一下，现在只有三个视频，我想把第一个视频摆放在第一行的中间，然后把第二、三个视频摆放在第二行。那么就可以使用下面两个命令了。

ffmpeg -f lavfi -i color=c=black:s=640x720 -vframes 1 black.png

ffmpeg -i black.png -i top_center.mp4 -i bottom_left.mp4 -i bottom_right.mp4
-lavfi "[0:v][1:v][0:v]hstack=inputs=3[top];[2:v][3:v]hstack[bottom];[top][bottom]vstack"
-shortest 3_videos_2x2_grid.mp4

合并音频和视频
ffmpeg -i video.mp4 -i audio.wav -c:v copy -c:a aac -strict experimental output.mp4

如果视频中已经包含了音频，这个时候还可以替换视频中的音频，使用下面命令行。
ffmpeg -i video.mp4 -i audio.wav -c:v copy -c:a aac -strict experimental
-map 0:v:0 -map 1:a:0 output.mp4

合并两个音频
ffmpeg -i input1.mp3 -i input2.mp3 -filter_complex amerge -ac 2 -c:a libmp3lame -q:a 4 output.mp3

获取视频中的音频
ffmpeg -i input.mp4 -vn -y -acodec copy output.m4a

去掉视频中的音频
ffmpeg -i input.mp4 -an output.mp4

现在介绍，怎么合并两个视频并保留两个视频中的音频。也就是抖音中的合拍功能。
1.合并两个视频，但是发现只有一个声音。无所谓。
2.抽取两个视频中的音频，然后合并成一个音频。
3.将这个音频替换到之前的合并视频中。
4.ok了。
5.可以使用ffplay播放了。

关于合成音视频的原理，
我猜测是 https://gitlab.websupport.sk/peter.kovar/ffmpeg-mvc/commit/319440e54f47891a1ab27ffb73d783895f225fe8

作者：匿名用户_bcc3
链接：https://www.jianshu.com/p/2a824f13b2af
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```