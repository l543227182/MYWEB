
var weatherPublic = function(){
	this.infoInit.apply(this, arguments);
};
weatherPublic.prototype = {
	
	$ : function(gid){
		return document.getElementById(gid);
	},
	
	getServerTime : function(){
		this.serverTime = new Date(2016, 03-1, 23, 18, 28, 55);
	},
	//加载css
	LoadCss : function(){
		var cssFile=document.createElement("link");
		cssFile.setAttribute("rel", "stylesheet");
		cssFile.setAttribute("type", "text/css");
		cssFile.setAttribute("href", "http://www.qq.com/css/qq2012/djakPublic.css");
		document.getElementsByTagName("head")[0].appendChild(cssFile);
		
		var style = document.createElement('style');
		style.type = "text/css";
		var styles = '.rightAikanTab{width:'+this.djakWidth+'px;}' +
					'.rightAikanTab .bd dl{width:'+this.djakWidth+'px;}'+
					'.rightAikanTab .bd dl.selected dd.aikanTips .fl{width:'+(this.djakWidth-120)+'px;}'+
					'.rightAikanTab .bd dl.selected dd.aikanTips .fl a{width:'+(this.djakWidth-120)+'px;}';
		(document.getElementsByTagName('head')[0] || document.body).appendChild(style);
		if(style.styleSheet){
			style.styleSheet.cssText = styles;
		}else{
			style.appendChild(document.createTextNode(styles));
		}
	},
	//加载js
	LoadScript : function(url, callback){
		var script = document.createElement("script");
		script.type = "text/javascript";
		if (script.readyState){
			script.onreadystatechange = function(){   
			   if(script.readyState == "loaded" || script.readyState == "complete"){   
					script.onreadystatechange = null; 
					callback();
					document.getElementsByTagName("head")[0].removeChild(this);
			   }
			};
		}else {
			script.onload = function(){
				callback();
				document.getElementsByTagName("head")[0].removeChild(this);
			};
		}
		script.src = url;
		document.getElementsByTagName("head")[0].appendChild(script);
	},
	//天气信息初始化
	infoInit:function(options){
		this.cityNameLayout = options.cityNameLayout; // 城市名字容器id;
		this.currentTemperatureLayout = options.currentTemperatureLayout; //当前温度容器id
		this.currentWeatherTxtLayout = options.currentWeatherTxtLayout; //当前天气文字容器id
		this.currentWeatherIconLayout = options.currentWeatherIconLayout; //当前天气图标容器id
		this.currentWindDirectionLayout = options.currentWindDirectionLayout; //当前风向容器id
		this.currentWindLevelLayout = options.currentWindLevelLayout; //当前风级容器id
		var that = this;
		that.LoadScript("http://fw.qq.com/ipaddress?random="+Math.random(), function(){
			if(typeof(IPData) != "undefined"){
				that.ip = IPData[0];
				that.pro = IPData[2];
				switch(IPData[2]){
					case '北京市':
						that.city = IPData[2];
						break;
					case '上海市':
						that.city = IPData[2];
						break;
					case '重庆市':
						that.city = IPData[2];
						break;
					case '天津市':
						that.city = IPData[2];
						break;
					case '香港':
						that.city = IPData[2];
						break;
					case '澳门':
						that.city = IPData[2];
						break;
					default:
						that.city = IPData[3];
				}
			}
			var that2 = that;
			that2.LoadScript("http://mat1.gtimg.com/www/js/weather/weatherInfo_v1.0.js", function(){
				if(weatherMap[that2.pro]){
					if(weatherMap[that2.pro][that2.city]){
						that2.weatherId = weatherMap[that2.pro][that2.city];
					}else{
						that2.weatherId = weatherMap[that2.pro]["_"];
					}
				}else{
					that2.weatherId = weatherMap["defaultCity"];
				}
				var that3 = that2;
				that3.LoadScript("http://weather.gtimg.cn/city/" + that3.weatherId + ".js?ref=qqIndex", function(){
					that3.cityName = __weather_city["bi_name"]; //城市名字
					if(that3.cityName == "西安(陕西)"){
						that3.cityName = "西安";
					}
					that3.currentTemperature = __weather_city["sk_tp"]; //当前温度
					that3.currentWeatherTxt = weatherMap["weatherIcon"][__weather_city.sk_wt]["txt"]; //当前天气文字
					that3.currentWeatherIcon = weatherMap["weatherIcon"][__weather_city.sk_wt]["day"]; //当前天气图标
					that3.currentWindDirection = weatherMap["weatherWind"][__weather_city.sk_wd]["Dir"]; //当前风向
					that3.currentWindLevel = weatherMap["weatherWind"][__weather_city.sk_wp]["Power"]; //当前风离
					//向页面容器中填充天气信息
					if(that3.$(that3.cityNameLayout)){
						that3.$(that3.cityNameLayout).innerHTML = that3.cityName;
					}
					if(that3.$(that3.currentTemperatureLayout)){
						that3.$(that3.currentTemperatureLayout).innerHTML = that3.currentTemperature;
					}
					if(that3.$(that3.currentWeatherTxtLayout)){
						that3.$(that3.currentWeatherTxtLayout).innerHTML = that3.currentWeatherTxt;
					}
					if(that3.$(that3.currentWeatherIconLayout)){
						that3.$(that3.currentWeatherIconLayout).innerHTML = '<img src="' + that3.currentWeatherIcon + '" />';
					}
					if(that3.$(that3.currentWindDirectionLayout)){
						that3.$(that3.currentWindDirectionLayout).innerHTML = that3.currentWindDirection;
					}
					if(that3.$(that3.currentWindLevelLayout)){
						that3.$(that3.currentWindLevelLayout).innerHTML = that3.currentWindLevel;
					}
				});
			});
		});
	}
};
var _serverTime = new Date();
