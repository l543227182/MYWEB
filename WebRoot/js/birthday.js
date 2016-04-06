function pageInit()
{
	makeYear();
	makeMonth();
	makeDay();
	
}

function makeYear(){
	
	var year = document.getElementById("year");
	var y=new Date().getFullYear();
	//alter(y);
	for(var i=y;i>=1950;i--){
		var option = document.createElement("option");
		option.value = i;
		option.innerText = i;
		year.appendChild(option);
	}
}

function makeMonth()
{
	var month = document.getElementById("month");
	for(var i=2;i<=12;i++)
	{
		var option = document.createElement("option");
		if(i<10)
		{
			option.value = '0' + i;
			option.innerText = '0' + i;
		}else{
			option.value = i;
			option.innerText = i;
		}
		month.appendChild(option);
	}
}

function makeDay()
{
	var day = document.getElementById("day");
	for(var i=2;i<=31;i++)
	{
		var option = document.createElement("option");
		if(i<10)
		{
			option.value = '0' + i;
			option.innerText = '0' + i;
		}else{
			option.value = i;
			option.innerText = i;
		}
		day.appendChild(option);
	}
}

function dosubmit()
{
	makeBirthday();
	
	return true;
}

function makeBirthday(){
	
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var day = document.getElementById("day").value;
	
	var birthday = year + "-" + month + "-" + day;
	
	var input = document.createElement("input");
	input.type="hidden";
	input.name = "user.birthday";
	input.value = birthday
	
	document.getElementById("register").appendChild(input);
	
}
