//市局、分局首页通用js

$(function(){
	$('.Phot_left li').click(function(){
		var hef = $(this).find('img').attr('src');
		var tit = $(this).attr('title');
      	var text = $(this).find('span').attr('span');
      	$('.Phot_sk span').text(tit);
      	$('.Phot_sk .penl').text(text);
//   	$('.Phot_right img').stop(true,true).fadeOut(200).fadeIn(200);
      	$('.Phot_right img').attr('src',hef);
 	});
        
	$('.Phot_right').hover(function(){
		$('.Phot_sk').stop(true,true).animate({top:'4px'});
	},function(){
		$('.Phot_sk').stop(true,true).animate({top:'-289px'});
	});
});

$("#pic_list_1").cxScroll();
$("#pic_list_2").cxScroll({direction:"left",step:1});

$(".menu li").click(function(){
	$(".menu li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu li").index(this);
	$(".tab").fadeOut(0);
    $(".menu li a").fadeOut(0);
	$(".tab").eq(index).fadeIn(0);
    $(".menu li a").eq(index).fadeIn(0);
})

$(".menu1 li").click(function(){
	$(".menu1 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu1 li").index(this);
	$(".tab1").fadeOut(0);
    $(".menu1 li a").fadeOut(0);
	$(".tab1").eq(index).fadeIn(0);
    $(".menu1 li a").eq(index).fadeIn(0);
})

$(".menu2 li").click(function(){
	$(".menu2 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu2 li").index(this);
	$(".tab2").fadeOut();
    $(".menu2 li a").fadeOut();
	$(".tab2").eq(index).fadeIn();
    $(".menu2 li a").eq(index).fadeIn();
})

$(".menu3 li").click(function(){
	$(".menu3 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu3 li").index(this);
	$(".tab3").fadeOut();
    $(".menu3 li a").fadeOut();
	$(".tab3").eq(index).fadeIn();
    $(".menu3 li a").eq(index).fadeIn();
})

$(".menu4 li").click(function(){
	$(".menu4 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu4 li").index(this);
	$(".tab4").fadeOut(0);
    $(".menu4 li a").fadeOut(0);
	$(".tab4").eq(index).fadeIn(0);
    $(".menu4 li a").eq(index).fadeIn(0);
})

$(".menu5 li").click(function(){
	$(".menu5 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu5 li").index(this);
	$(".tab5").fadeOut(0);
    $(".menu5 li a").fadeOut(0);
	$(".tab5").eq(index).fadeIn(0);
    $(".menu5 li a").eq(index).fadeIn(0);
})

$(".menu6 li").mouseenter(function(){
	$(".menu6 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu6 li").index(this);
	$(".tab6").fadeOut();
    //$(".menu6 li a").fadeOut();
	$(".tab6").eq(index).fadeIn();
    //$(".menu6 li a").eq(index).fadeIn();
})

$(".menu7 li").mouseenter(function(){
	$(".menu7 li").addClass("others");
	$(this).removeClass("others").addClass("tabs");
	var index = $(".menu7 li").index(this);
	$(".tab7").fadeOut();
    //$(".menu7 li a").fadeOut();
	$(".tab7").eq(index).fadeIn();
    //$(".menu7 li a").eq(index).fadeIn();
})

function ScrollImgLeft(){ 
    var speed=30; 
    var scroll_begin = document.getElementById("scroll_begin"); 
    var scroll_end = document.getElementById("scroll_end"); 
    var scroll_div = document.getElementById("scroll_div"); 
    scroll_end.innerHTML=scroll_begin.innerHTML; 
    function Marquee(){ 
        if(scroll_end.offsetWidth-scroll_div.scrollLeft<=0) 
            scroll_div.scrollLeft-=scroll_begin.offsetWidth; 
        else 
            scroll_div.scrollLeft++; 
    } 
    var MyMar=setInterval(Marquee,speed); 
    scroll_div.onmouseover=function() {clearInterval(MyMar);} 
    scroll_div.onmouseout=function() {MyMar=setInterval(Marquee,speed);} 
}
ScrollImgLeft();

jQuery(".txtMarquee-left").slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",vis:5,interTime:10});

jQuery(".slideTxtBox").slide({effect:"top",trigger:"click"});

var t = n =0, count;
$(document).ready(function(){ 
	count=$("#banner_list a").length;
	$("#banner_list a:not(:first-child)").hide();
	$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
	$("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")});
	$("#banner li").click(function() {
		var i = $(this).text() -1;//获取Li元素内的值，即1，2，3，4
		n = i;
		if (i >= count) return;
		$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
		$("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")})
		$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
		document.getElementById("banner").style.background="";
		$(this).toggleClass("on");
		$(this).siblings().removeAttr("class");
	});
	t = setInterval("showAuto()", 4000);
	$("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 4000);});
})
function showAuto(){
	n = n >=(count -1) ?0 : ++n;
	$("#banner li").eq(n).trigger('click');
}