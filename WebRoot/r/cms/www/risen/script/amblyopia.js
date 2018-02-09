
(function (f) {
	function j() {
		if (!f.cookie("_first_amblyopia")) {
			f.cookie("_first_amblyopia", "true");
		}
		if (!f.cookie("zoom")) {
			f.cookie("zoom", 1);
		}
	}
	j();
	var i = navigator.userAgent.toLowerCase(), b = function (k) {
		return k.test(i);
	}, g = document.documentMode, a = b(/opera/), c = !a && b(/msie/), e = c && (b(/msie 7/) || g == 7), d = c && (b(/msie 8/) && g != 7 && g != 9 || g == 8);
	var h = window.Amblyopia = function (k) {
		return h.fn.init(k);
	};
	h.fn = h.prototype = {_url:"swf/", _domain:"localhost", _tool_tips:true, _historyCount:5, _size:12, _size_max:16, _size_min:12, _cookiePath:"/", _isChange:true, _colors:{"#000000_#ffffff_#0000ff":"\u767d\u5e95\u9ed1\u5b57\u84dd\u94fe\u63a5", "#ffff00_#0000ff_#ffffff":"\u84dd\u5e95\u9ec4\u5b57\u767d\u94fe\u63a5", "#000000_#fefecc_#0000ff":"\u9ec4\u5e95\u9ed1\u5b57\u84dd\u94fe\u63a5", "#ffff00_#000000_#ffffff":"\u9ed1\u5e95\u9ec4\u5b57\u767d\u94fe\u63a5", other_other_other:"\u9875\u9762\u539f\u59cb\u914d\u8272"}, _color:"other_other_other", _mask:true, _mp3:"mp3/", _loading:"\u52a0\u8f7d\u4e2d..", _limit:1000 - 20, _frameDepth:0, _tagName:"label", _tagClass:"demo-class", _nodeText:[], _nodes:[], _areaIndex:1, _href:"", _nodeTypeBreaker:"", _queue:[], _dot:[",", "!", "?", ";", "\uff0c", "\u3002", "\uff1f", "\uff01", "\uff1b", ":", "\uff1a", "\n", "\u3001", "'", "\""], _tagNames:["BODY", "A", "LABEL", "BUTTON", "INPUT", "TD", "LI", "P", "H1", "H2", "H3", "UL", "TABLE", "DIV", "SPAN", "SELECT", "TEXTAREA"], _attachEvent:function () {
		var k = this;
		f(document).bind("mousedown", this._pageClick);
		f("#x-toolbar > a").mousedown(function (l) {
			if (!this.id) {
				return;
			}
			if (f.inArray(this.id, ["x-sound-more", "x-palette", "x-palette-more"]) == -1) {
				k._pageClick();
			}
			switch (this.id) {
			  case "x-reset":
				k._reset();
				break;
			  case "x-undo":
				k._undo();
				break;
			  case "x-redo":
				k._redo();
				break;
			  case "x-font-max":
				k._addFont();
				break;
			  case "x-font-min":
				k._removeFont();
				break;
			  case "x-refresh":
				k._refresh();
				break;
			  case "x-view":
				k._view();
				break;
			  case "x-tips":
				k.tips_start();
				break;
			  case "x-palette":
			  case "x-palette-more":
				k._palette();
				break;
			  case "x-zoom-in":
				k._zoomIn();
				break;
			  case "x-zoom-out":
				k._zoomOut();
				break;
			  case "x-state":
				k._state_start();
				break;
			  default:
			}
			l.preventDefault();
			l.stopPropagation();
		});
		f(".panel").mousedown(function (q) {
			var o = k._getDom(q.target);
			if (!this.id || !o || !o.id) {
				return;
			}
			switch (this.id) {
			  case "x-sound-panel":
				switch (o.id) {
				  case "x-volume-add":
					k._volume_add();
					break;
				  case "x-volume-sub":
					k._volume_sub();
					break;
				  case "x-speed-add":
					k._speed_add();
					break;
				  case "x-speed-sub":
					k._speed_sub();
					break;
				}
				break;
			  case "x-palette-panel":
				var l = o.id.split("_");
				k._fontcolor = l[0];
				k._bgcolor = l[1];
				k._linkcolor = l[2];
				var p = k._fontcolor + "_" + k._bgcolor + "_" + k._linkcolor;
				var n = k._fontcolor.split("#")[1] + "_" + k._bgcolor.split("#")[1];
				var m = f.cookie("color");
				if (p == m) {
					return;
				}
				f.cookie("color", p);
				f(o).parent().children().removeClass("active").end().end().addClass("active");
				k._read_color();
				if (p == "other_other_other") {
					k._tool_play("color/other_other.mp3", true, function () {
						f(document).unmask();
						k._reload();
					});
					k._call(k._reload);
					k._close_palette_panel(false);
					f.cookie("palette", null);
					k._init_color();
					return;
				}
				k._init_color();
				k._dom(document.getElementById("frame-main"), k._changeColor, function (r) {
					return h.fn._leafNode(r);
				}, 0);
				k._tool_play("color/" + n + ".mp3");
				k._close_palette_panel(false);
				f.cookie("palette", null);
				break;
			}
			q.preventDefault();
			q.stopPropagation();
		});
		f("#tooltips .tooltips-close").click(function () {
			f.cookie("state", null);
			k._state_close();
			k._un_state();
			k._tool_play("amblyopia/_state_close.mp3");
		});
	}, _read_color:function () {
		var l = f.cookie("color") || this._color, k = l.split("_");
		if (k[0] == "other") {
			this._repeat_color = "#FFFFFF";
			this._repeat_bgcolor = "#000000";
			this._readed_fontcolor = "";
			this._readed_bgcolor = "";
			this._readed_linkcolor = "";
		} else {
			this._repeat_color = k[1];
			this._repeat_bgcolor = k[0];
			this._readed_fontcolor = k[0];
			this._readed_bgcolor = k[1];
			this._readed_linkcolor = k[2];
		}
	}, _setColor:function () {
		this._isChange = true;
		var k = f.cookie("color");
		if (!k || k == "other_other_other") {
			this._isChange = false;
			return;
		}
		var l = k.split("_");
		this._fontcolor = l[0];
		this._bgcolor = l[1];
		this._linkcolor = l[2];
	}, _changeColor:function (k) {
		if (k.tagName == "undefined") {
			return;
		}
		if (f.inArray(k.tagName, h.fn._tagNames) == -1) {
			return;
		}
		k.style.color = k.tagName == "A" ? h.fn._linkcolor : h.fn._fontcolor;
		k.style.background = "none";
		k.style.backgroundColor = h.fn._bgcolor;
	}, _getDom:function (k) {
		while (k && k.tagName != "BODY") {
			if (k.tagName == "LI") {
				return k;
			}
			k = k.parentNode;
		}
		return null;
	}, _reset:function () {
		var l = ["history", "historyIndex", "historyVisitPage", "view", "font", "tips", "palette", "color", "zoom", "state"], k = new Date(), n = this, m = function () {
			k.setTime(77771564221);
			f.each(l, function () {
				f.cookie(this, null, {expires:k.toGMTString()});
			});
			n.tips_close();
			n._font = n._size;
			n._activeFont();
			n._activeZoom(1);
			n._state_close();
			n._init_color();
			n._reload();
		};
		this._tool_play("amblyopia/_reset.mp3", true, function () {
			f(document).unmask();
			m();
		});
		this._call(m);
	}, _setFont:function (k) {
		if (k.nodeType == 3 && !(h.fn._internalNode(k))) {
		} else {
			if (k.tagName == "A" || k.tagName == h.fn._tagName.toUpperCase()) {
				if (h.fn._font == h.fn._size_min) {
					f(k).css({"font-size":"", "line-height":""});
				} else {
					f(k).css({"font-size":h.fn._font + "px", "line-height":h.fn._font + 4 + "px"});
				}
			}
		}
	}, _activeFont:function () {
		f("#x-font-max, #x-font-min").removeClass("btn-ico-active");
		if (this._font > this._size) {
			f("#x-font-max").addClass("btn-ico-active");
		}
		if (this._font < this._size) {
			f("#x-font-min").addClass("btn-ico-active");
		}
	}, _changeFont:function (k) {
		this._font = parseInt(f.cookie("font")) || this._size;
		if (k == "add") {
			if (this._font == this._size_max) {
				return;
			}
			this._font += 2;
			if (this._font > this._size_max) {
				this._font = this._size_max;
			}
		}
		if (k == "remove") {
			if (this._font == this._size_min) {
				return;
			}
			this._font -= 2;
			if (this._font < this._size_min) {
				this._font = this._size_min;
			}
		}
		this._activeFont();
		this._dom(document.getElementById("frame-main"), this._setFont, function (l) {
			return h.fn._leafNode(l);
		}, 0);
		f.cookie("font", this._font);
	}, _addFont:function () {
		this._changeFont("add");
		var l = "", k = "";
		switch (this._font) {
		  case this._size_max:
			k = "max";
			l = "\u5df2\u653e\u81f3\u6700\u5927";
			break;
		  default:
			k = this._font;
			l = "\u653e\u5927\u4e3a" + this._font + "\u50cf\u7d20";
		}
		this._tool_play("amblyopia/_size_add_" + k + ".mp3");
		this._tooltips("\u9875\u9762\u5b57\u4f53" + l);
	}, _removeFont:function () {
		this._changeFont("remove");
		var l = "", k = "";
		switch (this._font) {
		  case this._size_min:
			k = "min";
			l = "\u5df2\u7f29\u81f3\u6700\u5c0f";
			break;
		  default:
			k = this._font;
			l = "\u7f29\u5c0f\u4e3a" + this._font + "\u50cf\u7d20";
		}
		this._tool_play("amblyopia/_size_sub_" + k + ".mp3");
		this._tooltips("\u9875\u9762\u5b57\u4f53" + l);
	}, _setPosition:function (k, l) {
		f(".page-x").css({top:0, left:k});
		f(".page-y").css({top:l, left:0});
	}, _moveEvent:function (o) {
		var m = f(".page-x").outerWidth(true), n = f(".page-y").outerHeight(true), l = o.clientX - m, k = o.clientY - n;
		h.fn._setPosition(l, k);
	}, _moveIframe:function (p) {
		var n = f(".page-x").outerWidth(true), o = f(".page-y").outerHeight(true), m = f("#x-toolbar").outerHeight(true), l = p.clientX - n, k = p.clientY - o + m;
		h.fn._setPosition(l, k);
	}, _call:function (l) {
		var k = f.cookie("sound");
		if (k != "true") {
			l && l();
		} else {
			f(document).mask(this._loading);
		}
	}, _reload:function () {
		window.frames["frame-main"].location.reload();
	}, _refresh:function (k) {
		this._clear_tool_read();
		this._call(this._reload);
		this._tool_play("amblyopia/_refresh.mp3", true, function () {
			f(document).unmask();
			h.fn._reload();
		});
	}, _view:function () {
		if (f.cookie("view")) {
			this._tool_play("amblyopia/_view_data.mp3");
			this._tool_play("amblyopia/_view_data.mp3", true, function () {
				f(document).unmask();
				h.fn._reload();
			});
			this._call(this._reload);
			f("#x-view").attr("title", "\u7eaf\u6587\u672c\u6a21\u5f0f").find("ins").removeClass("x_view_data").addClass("x_view_text");
			f.cookie("view", null);
		} else {
			this._tool_play("amblyopia/_view_text.mp3");
			h.fn._view_text();
			f("#x-view").attr("title", "\u89c6\u56fe\u6a21\u5f0f").find("ins").removeClass("x_view_text").addClass("x_view_data");
			f.cookie("view", "true");
		}
	}, _view_normal:function () {
		var n = f("#frame-main").get(0).contentWindow, m = n.document, k = "view_text";
		for (var l = 0; l < m.styleSheets.length; l++) {
			m.styleSheets[l].disabled = false;
		}
		f("#frame-main").contents().find("span." + k).remove().end().find("." + k).removeClass(k).show();
	}, _view_text:function () {
		var r = f("#frame-main").get(0).contentWindow, s = r.document, u = "view_text", p = s.getElementsByTagName("img"), q = r.frames;
		var k = s.getElementsByTagName("*");
		for (var o = 0, n = k.length; o < n; o++) {
			var m = k[o];
			m.style.cssText = "";
			var t = r.getComputedStyle ? r.getComputedStyle(m, null).height : m.currentStyle.height;
			if (t != "auto") {
				m.style.height = "auto";
			}
		}
		f.each(p, function () {
			var l = s.createElement("span");
			l.className = u;
			l.innerHTML = this.alt || this.title || "";
			this.parentNode.insertBefore(l, this);
			this.className += u;
			this.style.display = "none";
		});
		f.each(q, function () {
			try {
				this.className += u;
				this.style.display = "none";
			}
			catch (l) {
			}
		});
		for (var o = 0; o < s.styleSheets.length; o++) {
			s.styleSheets[o].disabled = true;
		}
	}, tips_start:function () {
		if (f.cookie("tips")) {
			f.cookie("tips", null);
			this.tips_close();
			this._tool_play("amblyopia/_tips_close.mp3");
		} else {
			f.cookie("tips", "true");
			this.tips_open();
			this._tool_play("amblyopia/_tips_open.mp3");
		}
	}, tips_open:function () {
		f(".page-x, .page-y").show();
		f("#x-tips").addClass("btn-ico-active");
		f(document).mousemove(this._moveEvent);
		f("#frame-main").contents().find("body").mousemove(this._moveIframe);
	}, tips_close:function () {
		f(".page-x, .page-y").hide();
		f("#x-tips").removeClass("btn-ico-active");
		f(document).unbind("mousemove", this._moveEvent);
		f("#frame-main").contents().find("body").unbind("mousemove", this._moveIframe);
	}, _clearAll:function () {
		this._stopAll();
		this._nodeText = [];
	}, _stopAll:function () {
		soundManager.stopAll();
	}, _chr:function (n) {
		var l = 0;
		var m = "";
		var k = "";
		if (!n) {
			return "";
		}
		for (l = 0; l < n.length; l++) {
			m = n.charAt(l);
			if (m == "\t" || m == " " || m == "\r" || m == "\n" || m == "$" || m == "#" || m == "|" || m == "\\" || m == "&") {
				m = "";
			} else {
				k += m;
			}
		}
		return k;
	}, simpleHash:function (o) {
		if (o == null) {
			return "nullhash";
		}
		var p = String(typeof o);
		if (p == "undefined") {
			return "undefinedhash";
		}
		if (p != "string") {
			o = String(o);
		}
		if (o.length <= 0) {
			return "emptystring";
		}
		var l = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_$";
		var k = "";
		var m;
		var t, s, q;
		for (var n = 0, r = o.length; n < r; n++) {
			m = o.charCodeAt(n);
			if ((m >= 48 && m <= 57) || (m >= 65 && m <= 90) || (m >= 97 && m <= 122)) {
				k += o.charAt(n);
			} else {
				t = m >> 10;
				s = (m & 1023) >> 4;
				q = m & 15;
				k += "$" + l.charAt(t) + l.charAt(s) + l.charAt(q);
			}
		}
		if (k.length > 64) {
			k = "_" + hex_md5(o);
		}
		return k;
	}, _play:function (n, m, o, l, q) {
		if (q != "callback") {
			this._clearAll();
		}
		var k = false;
		if (q == "callback") {
			k = true;
			q = function () {
			};
		}
		n += new Date().getTime();
		n = this.simpleHash(n);
		var p = soundManager.getSoundById(n);
		if (!p || p.readyState == 2) {
			var p = soundManager.createSound({id:n, url:m, autoLoad:l, stream:o, autoPlay:false, onload:function () {
			}, onplay:function () {
			}, onstop:function () {
			}, whileplaying:function () {
			}, onfinish:function () {
				this.destruct();
				q();
			}, volume:this._volume});
			if (k) {
				this._queue.push(p);
			} else {
				p.play();
			}
		} else {
			if (p.readyState == 0 || p.readyState == 1) {
				if (k) {
					this._queue.push(p);
				} else {
					p.autoPlay = o;
				}
			} else {
				if (p.readyState == 3) {
					if (k) {
						this._queue.push(p);
					} else {
						p.play();
					}
				}
			}
		}
		return p;
	}, _readyToPlay:function (m, k, n) {
		m = this._chr(m);
		if (m == "") {
			return false;
		}
		var l = m;
		var l = h.fn._mp3 + l;
		h.fn._play(m, l, true, false, n);
	}, _open_url:function (k) {
		this._stopAll();
		var l = this._isThisHref(k);
		if (l) {
			f("#frame-main").attr("src", k);
		} else {
		}
	}, _state_over:function () {
		if (this.tagName == h.fn._tagName.toUpperCase() && this.getAttribute("class") != h.fn._tagClass) {
			return;
		}
		var k = f(this);
		var l = h.fn.handleNode(this, true);
		if (!l) {
			l = k.text();
		}
		h.fn._tooltips(l);
		switch (this.tagName) {
		  case "SELECT":
			break;
		  case "IMG":
			k.css("border", "2px solid " + h.fn._break_bgcolor);
			break;
		  default:
			h.fn._text_color = this.style.color;
			h.fn._back_color = this.style.background;
			k.css({color:h.fn._break_color, background:h.fn._break_bgcolor, cursor:"default"});
		}
	}, _state_out:function () {
		var k = f(this);
		switch (this.tagName) {
		  case "SELECT":
			break;
		  case "IMG":
			k.css("border", "");
			break;
		  default:
			k.css({color:h.fn._text_color, background:h.fn._back_color, cursor:""});
		}
	}, _state_over_object:function () {
		var k = f(this);
		var l = h.fn.handleNode(k.children("object").get(0), true);
		if (!l) {
			l = k.text();
		}
		h.fn._tooltips(l);
	}, _state_out_object:function () {
	}, _on_state:function () {
		f("#frame-main").contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (k) {
			return f("img", this).length == 0;
		}).bind("mouseenter.State", this._state_over).bind("mouseleave.State", this._state_out).end().end().find("object").parent().bind("mouseenter.State", this._state_over_object).bind("mouseleave.State", this._state_out_object).end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).bind("mouseenter.State", this._state_over_object).bind("mouseleave.State", this._state_out_object).end().end().end().find("frame, iframe").filter(function (k) {
			return h.fn._isThisHref(f(this).attr("src"));
		}).contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (k) {
			return f("img", this).length == 0;
		}).bind("mouseenter.State", this._state_over).bind("mouseleave.State", this._state_out).end().end().find("object").parent().bind("mouseenter.State", this._state_over_object).bind("mouseleave.State", this._state_out_object).end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).bind("mouseenter.State", this._state_over_object).bind("mouseleave.State", this._state_out_object);
	}, _un_state:function () {
		f("#frame-main").contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (k) {
			return f("img", this).length == 0;
		}).unbind(".State").end().end().find("object").parent().unbind(".State").end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).unbind(".State").end().end().end().find("frame, iframe").filter(function (k) {
			return h.fn._isThisHref(f(this).attr("src"));
		}).contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (k) {
			return f("img", this).length == 0;
		}).unbind(".State").end().end().find("object").parent().unbind(".State").end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).unbind(".State");
	}, _addHistory:function (m, l) {
		for (var n = 0; n < l.length; n++) {
			var k = l[n].toString();
			if (k == m) {
				l.splice(n, 1);
				break;
			}
		}
		l.push(m);
		if (l.length > this._historyCount) {
			l.shift();
		}
		return l;
	}, _isThisHref:function (k) {
		if (typeof k == "undefined" || k == "" || k == "#") {
			return false;
		}
		if (k.indexOf("http://") === -1) {
			return true;
		}
		var o = this._domain, m = navigator.userAgent, l = ~m.indexOf("Opera"), n = document.attachEvent && !l;
		if (~o.indexOf(":")) {
			if (n) {
				o = o.split(":")[0];
			} else {
				o += "/";
			}
			if (~k.indexOf(o)) {
				return true;
			}
		} else {
			if (n) {
				if (~k.indexOf(o)) {
					return true;
				}
			} else {
				if (~k.indexOf(o) && (k.indexOf(o + ":") === -1 || ~k.indexOf(o + ":80/"))) {
					return true;
				}
			}
		}
		return false;
	}, _getHistoryCount:function () {
		var k = f.cookie("history");
		return k ? k.split("|").length : 0;
	}, _getHistoryType:function (l) {
		var k = f.cookie("history");
		k = k ? k.split("|") : [];
		if (k && k.length) {
			var m = parseInt(f.cookie("historyIndex"));
			switch (l) {
			  case "go":
				if (m >= k.length - 1) {
					return k[k.length - 1];
				} else {
					f.cookie("historyIndex", m + 1);
					return k[m + 1];
				}
				break;
			  case "back":
			  default:
				if (m < 1) {
					return k[0];
				} else {
					f.cookie("historyIndex", m - 1);
					return k[m - 1];
				}
			}
		}
		return false;
	}, _changeHistory:function () {
		var k = this._getHistoryCount(), l = f.cookie("historyIndex");
		if (k < 2) {
			f("#x-undo, #x-redo").addClass("btn-disabled");
			return;
		} else {
			if (k == 2) {
				if (l == 1) {
					f("#x-undo").removeClass("btn-disabled");
					f("#x-redo").addClass("btn-disabled");
				} else {
					f("#x-redo").removeClass("btn-disabled");
					f("#x-undo").addClass("btn-disabled");
				}
				return;
			}
		}
		if (l >= k - 1) {
			f("#x-undo").removeClass("btn-disabled");
			f("#x-redo").addClass("btn-disabled");
		} else {
			if (l < 1) {
				f("#x-redo").removeClass("btn-disabled");
				f("#x-undo").addClass("btn-disabled");
			} else {
				f("#x-undo, #x-redo").removeClass("btn-disabled");
			}
		}
	}, _record:function () {
		var k = window.frames["frame-main"].location.href.toLowerCase();
		if (!k) {
			return;
		}
		if (f.cookie("historyVisitPage")) {
			f.cookie("historyVisitPage", null);
			return;
		}
		var m = this._isThisHref(k);
		if (m) {
			var l = f.cookie("history"), n = parseInt(f.cookie("historyIndex"));
			l = l ? l.split("|") : [];
			l = this._addHistory(k, l);
			f.cookie("historyIndex", l.length - 1);
			f.cookie("history", l.join("|"));
		}
	}, _undo:function () {
		if (f("#x-undo").hasClass("btn-disabled")) {
			return;
		}
		var k = this._getHistoryType("back");
		if (k) {
			this._clear_tool_read();
			f.cookie("historyVisitPage", "true");
			this._changeHistory();
			this._tool_play("amblyopia/_undo.mp3");
			f("#frame-main").attr("src", k);
		}
	}, _redo:function () {
		if (f("#x-redo").hasClass("btn-disabled")) {
			return;
		}
		var k = this._getHistoryType("go");
		if (k) {
			this._clear_tool_read();
			f.cookie("historyVisitPage", "true");
			this._changeHistory();
			this._tool_play("amblyopia/_redo.mp3");
			f("#frame-main").attr("src", k);
		}
	}, _zoom:function (m) {
		var l = f("#frame-main").contents().find("body"), k = l.outerWidth(), n = l.outerHeight();
		if (f.browser.mozilla || f.browser.opera) {
			l.css({"margin-top":n * (m - 1) / 2, "-moz-transform":"scale(" + m + ")", "-o-transform":"scale(" + m + ")", transform:"scale(" + m + ")"});
		} else {
			if (d) {
				var p = f("#frame-main").contents().find("#x-scroll-setting").length;
				if (p < 1 && m > 1) {
					f("#frame-main").contents().find("body").append("<div id=\"x-scroll-setting\" style=\"height:1px;width:" + (k + 5) + "px\"></div>");
				} else {
					if (m <= 1 && p >= 1) {
						f("#frame-main").contents().find("#x-scroll-setting").remove();
					}
				}
			}
			l.css("zoom", m);
			f("#frame-main").contents().get(0).documentElement.scrollLeft = k * (m - 1) / 2;
			try {
				f("#frame-main").contents().get(0).body.scrollLeft = k * (m - 1) / 2;
			}
			catch (o) {
			}
		}
	}, _activeZoom:function (k) {
		f("#x-zoom-in, #x-zoom-out").removeClass("btn-ico-active");
		if (k > 1) {
			f("#x-zoom-in").addClass("btn-ico-active");
		}
		if (k < 1) {
			f("#x-zoom-out").addClass("btn-ico-active");
		}
	}, _zoomIn:function () {
		var l = Number(f.cookie("zoom")) || 1;
		l += 0.1;
		if (l > 1.5) {
			l = 1.5;
		}
		this._activeZoom(l);
		this._zoom(l);
		f.cookie("zoom", l);
		var k = parseInt(l * 100);
		var m = "";
		switch (k) {
		  case 100:
			m = "\u653e\u5927\u4e3a\u767e\u5206\u4e4b\u4e00\u767e";
			break;
		  case 110:
			m = "\u653e\u5927\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e00\u5341";
			break;
		  case 120:
			m = "\u653e\u5927\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e8c\u5341";
			break;
		  case 130:
			m = "\u653e\u5927\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e09\u5341";
			break;
		  case 140:
			m = "\u653e\u5927\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u56db\u5341";
			break;
		  case 150:
			m = "\u5df2\u7ecf\u653e\u81f3\u6700\u5927";
			break;
		}
		this._tooltips("\u9875\u9762" + m);
		this._tool_play("amblyopia/_zoom_in_" + k + ".mp3");
	}, _zoomOut:function () {
		var l = Number(f.cookie("zoom"));
		l -= 0.1;
		l = parseFloat(l).toFixed(2);
		if (l < 1) {
			l = 1;
		}
		this._activeZoom(l);
		this._zoom(l);
		f.cookie("zoom", l);
		var k = parseInt(l * 100);
		var m = "";
		switch (k) {
		  case 100:
			m = "\u5df2\u7ecf\u7f29\u81f3\u6700\u5c0f";
			break;
		  case 110:
			m = "\u7f29\u5c0f\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e00\u5341";
			break;
		  case 120:
			m = "\u7f29\u5c0f\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e8c\u5341";
			break;
		  case 130:
			m = "\u7f29\u5c0f\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e09\u5341";
			break;
		  case 140:
			m = "\u7f29\u5c0f\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u56db\u5341";
			break;
		  case 150:
			m = "\u7f29\u5c0f\u4e3a\u767e\u5206\u4e4b\u4e00\u767e\u4e94\u5341";
			break;
		}
		this._tooltips("\u9875\u9762" + m);
		this._tool_play("amblyopia/_zoom_out_" + k + ".mp3");
	}, _palette:function () {
		if (f.cookie("palette")) {
			f.cookie("palette", null);
			this._close_palette_panel();
		} else {
			f.cookie("palette", "true");
			this._show_palette_panel();
		}
	}, _show_palette_panel:function () {
		if (f("#x-palette, #x-palette-more").hasClass("btn-ico-active")) {
			return;
		}
		var l = f("#x-palette-panel"), m = f("#x-palette").offset();
		l.css("left", m.left).slideDown("slow", function () {
			var p = f(this), n = p.outerWidth(true), o = p.outerHeight(true);
			if (f.browser.msie) {
				f(".bg-iframe").css({left:m.left, width:n, height:o}).show();
			}
		});
		f("#x-palette, #x-palette-more").addClass("btn-ico-active");
		var k = f.cookie("color") || this._color;
		f("#x-palette-panel li").removeClass("active").filter(function () {
			return this.id == k;
		}).addClass("active");
		this._tool_play("amblyopia/_palette_open.mp3");
	}, _close_palette_panel:function (k) {
		if (!f("#x-palette, #x-palette-more").hasClass("btn-ico-active")) {
			return;
		}
		f(".bg-iframe").hide();
		f("#x-palette-panel").slideUp("slow");
		f("#x-palette, #x-palette-more").removeClass("btn-ico-active");
		var l = k == null ? true : !!k;
		l && this._tool_play("amblyopia/_palette_close.mp3");
	}, _pageClick:function (k) {
		if (f.cookie("palette")) {
			h.fn._close_palette_panel();
			f.cookie("palette", null);
		}
		if (f.cookie("soundMange")) {
			h.fn._close_sound_panel();
			f.cookie("soundMange", null);
		}
	}, _init_color:function () {
		var l = f.cookie("color") || this._color, k = l.split("_");
		if (k[0] == "other") {
			k[0] = "";
			k[1] = "";
			this._break_color = "#FFFFFF";
			this._break_bgcolor = "#000000";
		} else {
			this._break_color = k[1];
			this._break_bgcolor = k[0];
		}
		f(".page-x, .page-y").css("background-color", k[0]);
		f(".x-tooltips").css({color:k[0], "background-color":k[1]});
	}, _color_panel:function () {
		var k = [], l = this;
		f.each(this._colors, function (m, o) {
			var n = m.split("_");
			if (n[0] == "other") {
				k.push("<li id=\"" + m + "\"><div class=\"x-item\"><div class=\"x-box\"><ins>&nbsp;</ins><span>" + o + "</span></div></div></li>");
			} else {
				k.push("<li id=\"" + m + "\"><div class=\"x-item\"><div class=\"x-box\"><ins>&nbsp;</ins><span style=\"color:" + n[0] + ";background-color:" + n[1] + ";\">" + o + "</span></div></div></li>");
			}
		});
		f("#x-palette-panel").html("<div class=\"panel-block\"></div><div class=\"x-menu\"><ul>" + k.join("") + "</ul></div>");
	}, _query:function () {
		var k = soundManager;
		k.url = this._url;
		k.debugMode = (/debug=true/i).test(window.location.search) ? true : false;
		k.onready(function (l) {
			if (!l.success) {
				return;
			}
		});
		this._getCookie();
		this._attachEvent();
		this._color_panel();
		this._init_color();
		this._tool_read();
	}, _tool_play:function (l, k, m) {
		if (!m || typeof m != "function") {
			m = function () {
			};
		}
		k = k == null ? true : k;
		if (this._tool_tips) {
			this._clearAll();
			this._readyToPlay(l, k, m);
		}
	}, _tool_over:function () {
		if (!h.fn._tool_tips) {
			return false;
		}
		h.fn._clearAll();
		var k = f(this), m = k.attr("title"), l = h.fn._chr(m);
		if (l) {
			h.fn._tooltips(l);
		}
		var n = k.attr("id");
		if (n) {
			switch (n) {
			  case "x-undo":
				if (k.hasClass("btn-disabled")) {
					n = "x-undo-disabled";
				}
				break;
			  case "x-redo":
				if (k.hasClass("btn-disabled")) {
					n = "x-redo-disabled";
				}
				break;
			  case "x-view":
				if (k.find("ins").hasClass("x_view_text")) {
					n = "x-view-text";
				}
				if (k.find("ins").hasClass("x_view_data")) {
					n = "x-view-data";
				}
				break;
			  case "x-repeat":
				if (k.find("ins").hasClass("x_pause")) {
					n = "x-pause";
				}
				if (k.find("ins").hasClass("x_play")) {
					n = "x-play";
				}
				break;
			}
			h.fn._tool_play("toolbar/" + n + ".mp3");
		}
	}, _clear_tool_read:function () {
		f("#x-toolbar > a").unbind("mouseenter", this._tool_over);
		setTimeout(function () {
			f("#x-toolbar > a").bind("mouseenter", h.fn._tool_over);
		}, 1000);
	}, _tool_read:function () {
		var k = this;
		f("#x-toolbar > a").bind("mouseenter", this._tool_over);
		f("#x-palette-panel li").live("mouseenter", function () {
			k._clearAll();
			var l = f(this), m = l.text();
			m = k._chr(m);
			if (m) {
				k._tooltips(m);
			}
			var n = l.attr("id");
			if (n == "other_other_other") {
				n = "other";
			} else {
				n = n.split("_")[1].replace(/#/, "");
			}
			h.fn._tool_play("color/" + n + ".mp3");
		});
		f("#x-sound-panel li").live("mouseenter", function () {
			k._clearAll();
			var l = f(this), m = l.text();
			m = k._chr(m);
			if (m) {
				k._tooltips(m);
			}
			var n = l.attr("id");
			h.fn._tool_play("toolbar/" + n + ".mp3");
		});
	}, getNodeStyle:function (m, n, l) {
		if (typeof m != "undefined" && m.nodeType != 1) {
			m = m.parentNode;
		}
		if (m.currentStyle) {
			return m.currentStyle[n];
		} else {
			if (window.getComputedStyle) {
				var k = window.getComputedStyle(m, "");
				return k.getPropertyValue(l);
			} else {
				return "";
			}
		}
	}, isInvisible:function (l) {
		if (l == null || typeof l == "undefined" || l.nodeType != 1 || l.nodeName == "BODY") {
			return false;
		}
		var k = this.getNodeStyle(l, "display", "display");
		var m = this.getNodeStyle(l, "visibility", "visibility");
		if (k == "none" || m == "hidden" || l.offsetWidth <= 0 || !l.parentNode) {
			return true;
		}
		return false;
	}, getNumberOfListElements:function (n) {
		var k = 0;
		if (typeof n == "undefined" || typeof n.childNodes == "undefined") {
			return 0;
		}
		for (var m = 0, l = n.childNodes.length; m < l; m++) {
			if (n.childNodes[m].nodeName == "LI") {
				k++;
			}
		}
		return k;
	}, getTableName:function (l) {
		var k = "";
		if (l.caption) {
			k = this.handleChildNodes(l.caption);
		} else {
			if (l.summary) {
				k = l.summary;
			}
		}
		return k;
	}, getTableNum:function (n) {
		if (n != null) {
			var m = n.ownerDocument.getElementsByTagName("TABLE");
			for (var l = 0, k = m.length; l < k; l++) {
				if (m[l] == n) {
					return l;
				}
			}
		}
		return -1;
	}, getLargestRowLength:function (m) {
		var l = 0;
		for (var k = 0, n = m.length; k < n; k++) {
			if (m[k].cells.length > l) {
				l = m[k].cells.length;
			}
		}
		return l;
	}, hasAttribute:function (l, m) {
		if (l.hasAttribute) {
			return l.hasAttribute(m);
		}
		if (l.attributes && !(m in l.attributes)) {
			return false;
		}
		var k = l.getAttribute(m);
		if (k != null && k != "") {
			return true;
		}
		return false;
	}, handleNode:function (m, n) {
		if (!n) {
			return null;
		}
		var k = "";
		switch (m.nodeType) {
		  case 1:
			if (this.isInvisible(m)) {
				k = "";
			} else {
				k = this.handleElement(m);
			}
			break;
		  case 2:
			k = "";
			break;
		  case 3:
			var l = m.data;
			if (l.length > 0 && l.match(/[^\s,\.\?!:\-]/)) {
				l = l.replace(/&#\d+;/, "");
				k = l;
			} else {
				k = "";
			}
			break;
		  case 8:
		  case 9:
		  case 10:
		  default:
			k = "";
		}
		return k;
	}, handleChildNodes:function (n) {
		var k = "";
		for (var m = 0, l = n.childNodes.length; m < l; m++) {
			k += this.handleNode(n.childNodes[m], true) + " ";
			if (!this._leafNode(n.childNodes[m])) {
				k += this.handleChildNodes(n.childNodes[m]);
			}
		}
		return k;
	}, handleAreaNode:function (k) {
		if (k.getAttribute("alt")) {
			return k.getAttribute("alt");
		} else {
			if (k.getAttribute("href")) {
				return k.getAttribute("href");
			} else {
				return "";
			}
		}
	}, handleImageNode:function (k) {
		if (k.getAttribute("alt")) {
			return k.getAttribute("alt");
		} else {
			return "";
		}
	}, handleListNode:function (p) {
		var k = "";
		var o = p.parentNode;
		while (o && o.tagName != "BODY") {
			if (o.tagName == "OL") {
				var l = o.getElementsByTagName("LI");
				for (var m = 0, n = l.length; m < n; m++) {
					if (l[m] == p) {
						k += (m + 1) + ". ";
						break;
					}
				}
				break;
			}
			o = o.parentNode;
		}
		return k;
	}, handleInputNode:function (l) {
		var k = "";
		switch (l.getAttribute("type")) {
		  case "button":
			k += "\u6309\u94ae:" + l.value;
			break;
		  case "checkbox":
			k += "\u590d\u9009\u6309\u94ae: " + ((l.checked == true) ? "\u5df2\u9009\u4e2d" : "\u672a\u9009\u4e2d");
			break;
		  case "file":
			k += "\u6587\u4ef6\u4e0a\u4f20\u9009\u62e9\u6309\u94ae:" + l.value;
			break;
		  case "hidden":
			break;
		  case "image":
			k += "\u56fe\u7247\u6309\u94ae:" + l.value;
			break;
		  case "password":
			k += "\u5bc6\u7801\u8f93\u5165\u6846";
			break;
		  case "radio":
			k += "\u5355\u9009\u6309\u94ae:" + l.value;
			break;
		  case "reset":
			k += "\u91cd\u7f6e\u6309\u94ae:" + l.value;
			break;
		  case "submit":
			k += "\u63d0\u4ea4\u6309\u94ae:" + l.value;
			break;
		  case "text":
		  default:
			k += "\u6587\u672c\u8f93\u5165\u6846:" + l.value;
		}
		return k;
	}, handleElement:function (n) {
		var k = "";
		switch (n.tagName) {
		  case "A":
			var l = this.handleChildNodes(n);
			var m = n.getAttribute("title");
			if (this.hasAttribute(n, "title") && m.length > l.length) {
				k += "\u94fe\u63a5 " + this._nodeTypeBreaker + m;
			} else {
				k += "\u94fe\u63a5 " + this._nodeTypeBreaker + l;
			}
			break;
		  case "AREA":
			k += "\u94fe\u63a5\u533a\u57df " + this._nodeTypeBreaker + this.handleAreaNode(n);
			break;
		  case "BUTTON":
			k += this.handleChildNodes(n) + " \u6309\u94ae";
			break;
		  case "OBJECT":
			k = n.getAttribute("title") ? n.getAttribute("title") : "";
			k = "\u5a92\u4f53 " + k;
			break;
		  case "EMBED":
			k = n.getAttribute("title") ? n.getAttribute("title") : "";
			k = "\u5a92\u4f53 " + k;
			break;
		  case "H1":
			k += "1\u53f7\u6807\u9898";
			break;
		  case "H2":
			k += "2\u53f7\u6807\u9898";
			break;
		  case "H3":
			k += "3\u53f7\u6807\u9898";
			break;
		  case "H4":
			k += "4\u53f7\u6807\u9898";
			break;
		  case "H5":
			k += "5\u53f7\u6807\u9898";
			break;
		  case "H6":
			k += "6\u53f7\u6807\u9898";
			break;
		  case "IMG":
			var r = this.handleImageNode(n);
			if (r && r.length > 0) {
				k += "\u56fe\u7247 " + this._nodeTypeBreaker + r;
			} else {
				k += "\u56fe\u7247";
			}
			break;
		  case "INPUT":
			k += this.handleInputNode(n);
			break;
		  case "LI":
			k += this.handleListNode(n);
			break;
		  case "SELECT":
			k += "\u5217\u8868\u6846 " + this._nodeTypeBreaker + n.options[n.selectedIndex].text + "\u9009\u9879";
			break;
		  case "TABLE":
			var o = n.rows.length;
			var p = this.getLargestRowLength(n.rows);
			if (o > 2 && p > 2) {
				k += "\u8868\u683c " + this.getTableNum(n) + " " + this.getTableName(n) + " \u5f00\u59cb " + o + " \u884c " + p + " \u5355\u5143\u683c ";
			}
			break;
		  case "TEXTAREA":
			k += "\u591a\u6587\u672c\u8f93\u5165\u6846\u5185\u5bb9\u4e3a: " + this._nodeTypeBreaker + n.value;
			break;
		  case "UL":
		  case "OL":
			var q = this.getNumberOfListElements(n);
			if (q > 0) {
				k += "\u5217\u8868 " + q + " \u6761\u76ee";
			}
		}
		if (n.getAttribute("title") && !k) {
			k = k + " " + n.getAttribute("title");
		}
		return k;
	}, _phrase:function (n) {
		var p = new Array();
		var m = 0;
		var v = 0;
		var o = 0;
		var q = "";
		if (n == "") {
			return [];
		}
		for (var u = 0, r = n.length; u < r; u++) {
			for (var t = 0, s = this._dot.length; t < s; t++) {
				if (n.charAt(u) == this._dot[t]) {
					o = u;
					q = n.slice(v, o);
					if (q.length >= 45) {
						while (q.length >= 45) {
							p[m] = q.slice(0, 45);
							m += 1;
							q = q.slice(45);
						}
						if (q != "") {
							p[m] = q;
							m += 1;
						}
					} else {
						p[m] = n.slice(v, o);
						m += 1;
					}
					v = o + 1;
					p[m] = n.charAt(u);
					m += 1;
					break;
				}
			}
		}
		if (v < n.length) {
			p[m] = n.slice(v);
		}
		return p;
	}, _phraseCount:function (k) {
		return (f.trim(k).split(/\s+/).length);
	}, _split:function (r) {
		if (typeof r != "string") {
			return [];
		}
		var n = this._phrase(r);
		var q = new Array();
		if (n.length < 2) {
			return n;
		}
		var l = 0;
		var m = 0;
		q[m] = "";
		for (var p = 0, o = n.length; p < o; p++) {
			l = this._phraseCount(n[p]);
			if (n[p].length < 5 || l < 1) {
				if ((q[m].length + n[p].length) <= 20) {
					q[m] += n[p];
				} else {
					if (n[p].length == 1) {
						q[m] += n[p];
					} else {
						m = m + 1;
						q[m] = "";
						q[m] += n[p];
					}
				}
			} else {
				if ((q[m].length + n[p].length) <= 20) {
					q[m] += n[p];
				} else {
					m = m + 1;
					q[m] = "";
					q[m] += n[p];
				}
			}
		}
		n.length = 0;
		if (q[0] == "") {
			q = q.slice(1);
		}
		return q;
	}, _dom:function (n, q, k, p) {
		if (p > this._limit) {
			return;
		}
		var l = [];
		if ((typeof k != "function") || !k(n)) {
			if (n.firstChild) {
				l.push(n.firstChild);
			}
		}
		if (n && n.nextSibling) {
			l.push(n.nextSibling);
		}
		if (n && n.nodeName.toLowerCase() == "iframe" && this._isThisHref(n.src)) {
			try {
				if (n.contentDocument) {
					l.push(n.contentDocument.body);
				} else {
					if (n.contentWindow) {
						l.push(n.contentWindow.document.body);
					} else {
						if (n.document) {
							l.push(n.document.body);
						}
					}
				}
			}
			catch (o) {
			}
		}
		if (p != this._limit && n) {
			try {
				q(n);
			}
			catch (o) {
			}
		}
		for (var m = 0; m < l.length; m++) {
			this._dom(l[m], q, k, p + 1);
		}
	}, _internalNode:function (l) {
		var k = l.parentNode;
		if (k) {
			if (k.nodeName == "OPTION" || k.nodeName == "SELECT") {
				return true;
			}
		}
		return false;
	}, _reLink:function (m) {
		if (m.nodeName.toLowerCase() == "iframe" && this._isThisHref(m.src)) {
			try {
				var o;
				this._frameDepth++;
				if (m.contentDocument) {
					o = m.contentDocument.body;
				} else {
					if (m.contentWindow) {
						o = m.contentWindow.document.body;
					} else {
						if (m.document) {
							o = m.document.body;
						}
					}
				}
				if (o) {
					o.isFrame = true;
					o.depth = this._frameDepth;
				}
			}
			catch (n) {
			}
		}
		if (m.nodeType == 1) {
			var k = m.parentNode;
			if (k && k.isFrame && k.depth) {
				m.isFrame = true;
				m.depth = k.depth;
			}
		}
		if ((m.tagName == "A" || m.tagName == "AREA")) {
			var l = this._isThisHref(m.href);
			m.target = l ? (m.isFrame && m.depth > 1 ? "_parent" : "_self") : "_blank";
		}
	}, _reDom:function (p) {
		if (p.nodeType == 3 && !(h.fn._internalNode(p))) {
			var v = p.data;
			if (/\S/.test(v)) {
				var x = p.ownerDocument;
				var n = x.createElement(h.fn._tagName);
				n.setAttribute("class", h.fn._tagClass);
				var q = h.fn._split(v);
				var k = [];
				for (var s = 0, r = q.length; s < r; s++) {
					if (/\S/.test(q[s])) {
						var u = x.createElement(h.fn._tagName);
						u.setAttribute("class", h.fn._tagClass);
						var w = x.createTextNode(q[s]);
						u.appendChild(w);
						k.push(u);
					}
				}
				var t = p.parentNode;
				if (p.nextSibling != null) {
					var m = p.nextSibling;
					for (var s = 0; s < k.length; s++) {
						t.insertBefore(k[s], m);
					}
				} else {
					for (var s = 0; s < k.length; s++) {
						t.appendChild(k[s]);
					}
				}
				t.removeChild(p);
			}
		} else {
			h.fn._reLink(p);
			h.fn._isChange && h.fn._changeColor(p);
		}
	}, isVisible:function (l) {
		if (l.nodeName == "BODY") {
			return true;
		}
		if (!l) {
			return false;
		} else {
			if (!l.parentNode) {
				return false;
			} else {
				if (l.style) {
					if (l.style.display == "none") {
						return false;
					} else {
						if (l.style.visibility == "hidden") {
							return false;
						}
					}
				}
			}
		}
		if (window.getComputedStyle) {
			var k = window.getComputedStyle(l, "");
			if (k.display == "none") {
				return false;
			}
			if (k.visibility == "hidden") {
				return false;
			}
		}
		var k = l.currentStyle;
		if (k) {
			if (k.display == "none") {
				return false;
			}
			if (k.visibility == "hidden") {
				return false;
			}
		}
		return this.isVisible(l.parentNode);
	}, _leafElement:function (k) {
		switch (k.tagName) {
		  case "A":
			if (k.childNodes[0] && k.childNodes[0].tagName == "IMG") {
				return false;
			} else {
				return true;
			}
		  case "AREA":
		  case "OBJECT":
		  case "EMBED":
		  case "BUTTON":
		  case "TEXTAREA":
		  case "IMG":
		  case "NOSCRIPT":
		  case "SELECT":
			return this.isVisible(k);
		  case "HEAD":
		  case "SCRIPT":
		  case "STYLE":
			return true;
		  case "IFRAME":
		}
		return !k.hasChildNodes();
	}, _leafNode:function (k) {
		return (!k || !k.nodeType || k.nodeType == 3) ? true : this._leafElement(k);
	}, _ready:function () {
		this._dom(document.getElementById("frame-main"), this._reDom, function (k) {
			return h.fn._leafNode(k);
		}, 0);
	}, _remove:function () {
		f(document).unmask();
		f("#frame-main").contents().find("body").append("<div id=\"x-mask-loading\"></div>");
		h.fn._mask = true;
	}, _get_length:function (n) {
		if (n == "") {
			return 0;
		}
		n = n.toLowerCase();
		var m = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", ",", ".", "?", ":", ";", "[", "]", "{", "}", "\"", "'", "<", ">", "\\", "/", " "];
		var l = 0;
		for (var k = 0; k < n.length; k++) {
			if (f.inArray(n.charAt(k), m) == -1) {
				l = l + 2;
			} else {
				l++;
			}
		}
		return l;
	}, _auto_size:function (l) {
		var k = this._get_length(l);
		if (k > 16 * 10) {
			this._change_size(10);
		} else {
			if (k > 16 * 9) {
				this._change_size(14);
			} else {
				if (k > 16 * 8) {
					this._change_size(16);
				} else {
					if (k > 16 * 7) {
						this._change_size(20);
					} else {
						if (k > 16 * 6) {
							this._change_size(30);
						} else {
							if (k > 16 * 5) {
								this._change_size(35);
							} else {
								if (k > 16 * 4) {
									this._change_size(40);
								} else {
									if (k > 16 * 3) {
										this._change_size(48);
									} else {
										if (k > 16) {
											this._change_size(65);
										} else {
											this._change_size(115);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}, _change_size:function (k) {
		f("#tooltips-show").css({"font-size":k + "px", "line-height":k + "px"});
	}, _tooltips:function (k) {
		this._auto_size(k);
		f("#tooltips-show").html(k);
	}, _report:function () {
		var k = function () {
			var l = window.frames["frame-main"].document.getElementsByTagName("TITLE")[0].innerHTML;
			h.fn._tool_play(l, false);
		};
		if (f.cookie("_first_amblyopia") == "true") {
			this._readyToPlay("amblyopia/_first.mp3", true, k);
			f.cookie("_first_amblyopia", null);
		} else {
			k();
		}
	}, add_sheet:function (l, m) {
		var k;
		m = m == null ? window : m;
		if (l.str) {
			k = m.document.createElement("style");
			k.setAttribute("type", "text/css");
			if (k.styleSheet) {
				m.document.getElementsByTagName("head")[0].appendChild(k);
				k.styleSheet.cssText = l.str;
			} else {
				k.appendChild(m.document.createTextNode(l.str));
				m.document.getElementsByTagName("head")[0].appendChild(k);
			}
			return k.sheet || k.styleSheet;
		}
	}, isCookieEnabled:function () {
		var o = navigator.userAgent.toLowerCase(), l = function (r) {
			return r.test(o);
		}, m = l(/opera/), n = l(/webkit/), q = true;
		if (m || n) {
			if (!navigator.cookieEnabled) {
				q = false;
			}
		} else {
			f.cookie("cookieid", "1", {expires:60});
			var k = f.cookie("cookieid");
			if (!k) {
				q = false;
			}
			var p = new Date();
			p.setTime(77771564221);
			f.cookie("cookieid", null, {expires:p.toGMTString()});
		}
		return q;
	}, _queryIframe:function () {
		if (typeof this.cookieEnabled == "undefined") {
			this.cookieEnabled = this.isCookieEnabled();
		}
		if (!this.cookieEnabled) {
			return;
		}
		var l = document.getElementById("frame-main").contentWindow;
		this._domain = l.location.host;
		this._frameDepth = 0;
		this._clearAll();
		this._record();
		this._setColor();
		this._ready();
		this._remove();
		this._getCookieIframe();
		f("#frame-main").contents().find("body").bind("mousedown", this._pageClick);
		var k = true;
		soundManager.onready(function (m) {
			if (m.success) {
				if (k) {
					h.fn._report();
					k = false;
				}
			}
		});
	}, _state_start:function () {
		var k = f.cookie("state");
		if (k) {
			f.cookie("state", null);
			this._state_close();
			this._un_state();
			this._tool_play("amblyopia/_state_close.mp3");
		} else {
			f.cookie("state", "true");
			this._state_open();
			if (f.cookie("sound") != "true") {
				this._on_state();
			}
			this._tool_play("amblyopia/_state_open.mp3");
		}
	}, _state_open:function () {
		f("#x-state").addClass("btn-ico-active");
		f("#tooltips").slideDown("slow", function () {
			f("html").addClass("show-state");
		});
	}, _state_close:function () {
		f("#x-state").removeClass("btn-ico-active");
		f("html").removeClass("show-state");
		f("#tooltips").slideUp("slow");
	}, _getCookie:function () {
		f.cookie("pause", null);
		f.cookie("soundMange", null);
		f.cookie("palette", null);
		if (f.cookie("tips")) {
			this.tips_open();
		}
		if (f.cookie("state")) {
			this._state_open();
		}
	}, _getCookieIframe:function () {
		this._changeHistory();
		if (f.cookie("tips")) {
			this.tips_open();
		}
		if (f.cookie("view")) {
			f("#x-view").attr("title", "\u89c6\u56fe\u6a21\u5f0f").find("ins").removeClass("x_view_text").addClass("x_view_data");
			this._view_text();
		}
		if (f.cookie("font")) {
			this._changeFont();
		}
		if (f.cookie("state") && f.cookie("sound") != "true") {
			this._on_state();
		}
		if (f.cookie("zoom")) {
			var k = f.cookie("zoom");
			this._activeZoom(k);
			this._zoom(k);
		}
	}, apply:function (m, n, l) {
		if (l) {
			this.apply(m, l);
		}
		if (m && n && typeof n == "object") {
			for (var k in n) {
				m[k] = n[k];
			}
		}
		return m;
	}, init:function (k) {
		this.cookieEnabled = this.isCookieEnabled();
		if (!this.cookieEnabled) {
			alert("\u6d4f\u89c8\u5668\u672a\u542f\u7528cookie\uff01");
			return;
		}
		this.apply(this, k);
		f(document).ready(function () {
			h.fn._query();
		});
		setInterval(function () {
			if (h.fn._mask && f("#frame-main").contents().find("#x-mask-loading").length == 0) {
				f(document).mask(h.fn._loading);
				h.fn._mask = false;
			}
		}, 5);
	}};
	h.fn.init.prototype = h.fn;
})(jQuery);

