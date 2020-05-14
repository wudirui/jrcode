;(function( jQuery, window, undefined ) {

	$.extend({
		calcNum : function(obj){
			return obj <=9 ? "0"+obj:obj;
		},
		weeks : ["星期一",'星期二','星期三','星期三','星期四','星期五','星期六','周日']
	})

	/** ??????
	 *   [] {}
	 *
	 *  */
	$.fn.extend({
		runTimer : function(){
			/** ????????? */
			var date = new Date();

			/** ??? */
			var year = date.getFullYear();
			/** ??? */
			var month = date.getMonth()+1;
			/** ???  */
			var today = date.getDate();
			/** ??? */
			var hh = date.getHours();
			/** ??? */
			var mm = date.getMinutes();
			/** ??? */
			var ss= date.getSeconds();
			/** ???? */
			var weekDay = date.getDay(); // 0

			/** ?????? */
			var timer = year+"年"+$.calcNum(month)+"月"+$.calcNum(today)+"日"+" "+$.calcNum(hh)+":"+$.calcNum(mm)+":"+$.calcNum(ss)
			 +" "+$.weeks[weekDay];

			$(this).html(timer);

			/**
			 * if(??)
			 *  ???true ?? false
			 *  ?? ????????? ????true ????false
			 * */
			if(it){
				/** ???????????? */
				clearTimeout(it);
			}

			var t = this;  // ???????? this???nowTime<span>

			var it = setTimeout(function(){
				 /** ????????????? */
				 // ????this??????
				 t.runTimer();
			}, 1000);
		}
	});


})( jQuery, window );