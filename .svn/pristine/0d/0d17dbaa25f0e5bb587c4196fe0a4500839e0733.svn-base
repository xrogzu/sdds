//取当前时间	
function Diff(date,id){
	var myDate = new Date();
	//格式化当前时间 得到"yyyy-mm-dd"
	var endDate = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
	//取此条通知公告的发布时间
	var startDate = date;
	//console.info(startDate);
	//格式化通知公告的发布时间 得到yyyy-mm-dd
	startDate = startDate.substr(0,10);
	//调用日期运算函数 得到日期之差
	if(DateDiff(endDate,startDate) <= 3){
		//console.info('日期差小于三天');
		//获取当前通过li的html内容
		var select = '#'+id;
		var tzggCon = $(select).html();
		
		//判断是否已经有了new的图标 之所以加判断 是因为我发现CMS自带的cms_content_list会执行两边，百思不得其解。
		if(tzggCon!="" && tzggCon!=null){
			//alert("tzggCon   值:"+tzggCon);
			if(tzggCon.indexOf("<img") < 0 && tzggCon.indexOf("<IMG") < 0){
				//如果满足日期满足条件，且没有new的图标，我们就加上new的图标，拼装后重写li
				//alert('前面不带new');
				$(select).html("<img src='/r/cms/www/risen/images/new.gif'>&nbsp;" + tzggCon);		
			}
		}
		
	}

}

//取当前时间	
function Diff2(date,className){
	//console.info('bbbb');
	var myDate = new Date();
	//格式化当前时间 得到"yyyy-mm-dd"
	var endDate = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
	//取此条通知公告的发布时间
	var startDate = date;
	//console.info(startDate);
	//格式化通知公告的发布时间 得到yyyy-mm-dd
	startDate = startDate.substr(0,10);
	//调用日期运算函数 得到日期之差
	if(DateDiff(endDate,startDate) <= 3){
		
		//console.info('日期差小于三天');
		//获取当前通过li的html内容
		var select = '.'+className;
		var tzggCon = $(select).html();
		
		//判断是否已经有了new的图标 之所以加判断 是因为我发现CMS自带的cms_content_list会执行两边，百思不得其解。
		if(tzggCon!="" && tzggCon!=null){
			//alert('1  '+tzggCon);
			if(tzggCon.indexOf("<img") < 0 && tzggCon.indexOf("<IMG") < 0){
				//如果满足日期满足条件，且没有new的图标，我们就加上new的图标，拼装后重写li
				//alert('前面不带new');
				$(select).html("<img src='/r/cms/www/risen/images/new.gif'>&nbsp;" + tzggCon);		
			}	
		}
		
	}

}

	 //日期函数
  	function DateDiff(d1,d2){
		  var day = 24 * 60 * 60 *1000;
		  try{  
			    var dateArr = d1.split("-");
			    var checkDate = new Date();
			    checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
			    var checkTime = checkDate.getTime();
			    var dateArr2 = d2.split("-");
			    var checkDate2 = new Date();
			    checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
			    var checkTime2 = checkDate2.getTime();
			    var cha = (checkTime - checkTime2)/day; 
			    return cha;
		    }catch(e){
		        return false;
		    }
	}