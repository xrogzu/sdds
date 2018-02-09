
(function (a) {
	Amblyopia.fn.apply(Amblyopia.fn, {_isRead:false, _volume:100, _volume_step:20, _volume_max:100, _volume_min:20, _speed:0, _speed_step:2, _speed_max:2, _speed_min:-2, _dataUrl:"/s/default.aspx", _dataMp3:"/s/mp3/", _breakInterval:0, _repeatInterval:0, _breakTimer:1000, _repeatTimer:1000, _repeatIndex:0, _repeatFlag:false, _repeatNodes:[], _repeatNode:"", _repeatNum:0, _runTwice:true, _req:true, _model:0, _repeatTagNames:["A", "IMG", "BUTTON", "INPUT", "LABEL", "AREA", "OBJECT", "EMBED", "SELECT", "TEXTAREA"], _attachEvent:function () {
		var b = this;
		a(document).bind("mousedown", this._pageClick);
		a("#x-toolbar > a").mousedown(function (c) {
			if (!this.id) {
				return;
			}
			if (a.inArray(this.id, ["x-sound-more", "x-palette", "x-palette-more"]) == -1) {
				b._pageClick();
			}
			switch (this.id) {
			  case "x-reset":
				b._reset();
				break;
			  case "x-undo":
				b._undo();
				break;
			  case "x-redo":
				b._redo();
				break;
			  case "x-font-max":
				b._addFont();
				break;
			  case "x-font-min":
				b._removeFont();
				break;
			  case "x-refresh":
				b._refresh();
				break;
			  case "x-view":
				b._view();
				break;
			  case "x-tips":
				b.tips_start();
				break;
			  case "x-sound":
				b._sound();
				break;
			  case "x-sound-more":
				b._close_panel("x-palette-panel");
				b._soundMore();
				break;
			  case "x-palette":
			  case "x-palette-more":
				b._close_panel("x-sound-panel");
				b._palette();
				break;
			  case "x-zoom-in":
				b._zoomIn();
				break;
			  case "x-zoom-out":
				b._zoomOut();
				break;
			  case "x-break":
				b._break();
				break;
			  case "x-repeat":
				b._repeat();
				break;
			  case "x-state":
				b._state_start();
				break;
			  default:
			}
			c.preventDefault();
			c.stopPropagation();
		});
		a(".panel").mousedown(function (i) {
			var g = b._getDom(i.target);
			if (!this.id || !g || !g.id) {
				return;
			}
			switch (this.id) {
			  case "x-sound-panel":
				switch (g.id) {
				  case "x-volume-add":
					b._volume_add();
					break;
				  case "x-volume-sub":
					b._volume_sub();
					break;
				  case "x-speed-add":
					b._speed_add();
					break;
				  case "x-speed-sub":
					b._speed_sub();
					break;
				}
				break;
			  case "x-palette-panel":
				var c = g.id.split("_");
				b._fontcolor = c[0];
				b._bgcolor = c[1];
				b._linkcolor = c[2];
				var h = b._fontcolor + "_" + b._bgcolor + "_" + b._linkcolor;
				var f = b._fontcolor.split("#")[1] + "_" + b._bgcolor.split("#")[1];
				var d = a.cookie("color");
				if (h == d) {
					return;
				}
				a.cookie("color", h);
				a(g).parent().children().removeClass("active").end().end().addClass("active");
				b._read_color();
				if (h == "other_other_other") {
					b._tool_play("color/other_other.mp3", true, function () {
						a(document).unmask();
						b._reload();
					});
					b._call(b._reload);
					b._close_palette_panel(false);
					a.cookie("palette", null);
					b._init_color();
					return;
				}
				b._init_color();
				b._dom(document.getElementById("frame-main"), b._changeColor, function (e) {
					return Amblyopia.fn._leafNode(e);
				}, 0);
				b._tool_play("color/" + f + ".mp3");
				b._close_palette_panel(false);
				a.cookie("palette", null);
				break;
			}
			i.preventDefault();
			i.stopPropagation();
		});
		a("#tooltips .tooltips-close").click(function () {
			a.cookie("state", null);
			b._state_close();
			b._un_state();
			b._tool_play("amblyopia/_state_close.mp3");
		});
	}, _reset:function () {
		var c = ["history", "historyIndex", "historyVisitPage", "view", "font", "read", "sound", "tips", "palette", "color", "zoom", "pause", "state"], b = new Date(), e = this, d = function () {
			b.setTime(77771564221);
			a.each(c, function () {
				a.cookie(this, null, {expires:b.toGMTString()});
			});
			e.tips_close();
			e._sound_close();
			e._font = e._size;
			e._activeFont();
			e._activeZoom(1);
			e._state_close();
			e._init_color();
			e._reload();
		};
		this._tool_play("amblyopia/_reset.mp3", true, function () {
			a(document).unmask();
			d();
		});
		this._call(d);
	}, _break:function () {
		if (a.cookie("sound") != "true" || a.cookie("read") == "break") {
			return;
		}
		a("#x-break").addClass("btn-ico-active");
		a.cookie("read", "break");
		a("#x-repeat").removeClass("btn-ico-active").find("ins").removeClass("x_pause x_play").addClass("x_repeat");
		this._break_open();
		this._tool_play("amblyopia/_break.mp3");
	}, _repeat:function () {
		if (a.cookie("sound") != "true") {
			return;
		}
		var c = a("#x-break"), b = a("#x-repeat"), e = b.find("ins");
		c.removeClass("btn-ico-active");
		b.addClass("btn-ico-active");
		if (e.hasClass("x_repeat")) {
			var d = a.cookie("read");
			if (d == "repeat") {
				return;
			}
			a.cookie("read", "repeat");
			e.removeClass("x_repeat").addClass("x_pause");
			this._repeat_open();
			this._tool_play("amblyopia/_repeat.mp3");
		} else {
			if (e.hasClass("x_pause")) {
				e.removeClass("x_pause").addClass("x_play");
				this._pause_start();
				this._tool_play("amblyopia/_pause.mp3");
			} else {
				if (e.hasClass("x_play")) {
					e.removeClass("x_play").addClass("x_pause");
					this._pause_start();
				}
			}
		}
	}, _pause_start:function () {
		var c = a.cookie("sound"), b = a.cookie("read");
		if (c != "true" || b == "break") {
			return;
		}
		if (a.cookie("pause")) {
			this._pause_close();
		} else {
			this._pause_open();
		}
	}, _pause_open:function () {
		this._tool_play("amblyopia/_pause_open.mp3");
		a.cookie("pause", "true");
		this._pauseAll();
		if (a.cookie("read") == "repeat") {
			this._repeatFlag = false;
		}
	}, _pause_close:function () {
		this._tool_play("amblyopia/_pause_close.mp3");
		a.cookie("pause", null);
		this._resumeAll();
		this._repeat_start();
		if (a.cookie("read") == "repeat") {
			this._repeatFlag = true;
		}
	}, _close_panel:function (b) {
		switch (b) {
		  case "x-sound-panel":
			Amblyopia.fn._close_sound_panel();
			a.cookie("soundMange", null);
			break;
		  case "x-palette-panel":
			Amblyopia.fn._close_palette_panel();
			a.cookie("palette", null);
			break;
		  default:
		}
	}, _sound:function () {
		if (a.cookie("sound") == "true") {
			a.cookie("sound", "false");
			this._sound_close();
			if (a.cookie("state")) {
				this._on_state();
			}
			this._readyToPlay("amblyopia/_sound_close.mp3", true, function () {
			});
		} else {
			a.cookie("sound", "true");
			this._sound_open();
			this._un_state();
			this._tool_play("amblyopia/_sound_open.mp3");
		}
	}, _volume_add:function () {
		if (a.cookie("sound") != "true") {
			return;
		}
		this._volume = Number(a.cookie("volume")) || this._volume;
		this._volume += this._volume_step;
		if (this._volume >= this._volume_max) {
			this._volume = this._volume_max;
		}
		a.cookie("volume", this._volume);
		var c = this._volume / this._volume_step;
		var b = "";
		switch (c) {
		  case 1:
			b = "\u589e\u52a0\u4e3a\u4e00";
			break;
		  case 2:
			b = "\u589e\u52a0\u4e3a\u4e8c";
			break;
		  case 3:
			b = "\u589e\u52a0\u4e3a\u4e09";
			break;
		  case 4:
			b = "\u589e\u52a0\u4e3a\u56db";
			break;
		  case 5:
			b = "\u5df2\u589e\u81f3\u6700\u5927";
			break;
		}
		this._tool_play("amblyopia/_volume_add_" + c + ".mp3");
		this._tooltips("\u97f3\u91cf" + b);
	}, _volume_sub:function () {
		if (a.cookie("sound") != "true") {
			return;
		}
		this._volume = Number(a.cookie("volume")) || this._volume;
		this._volume -= this._volume_step;
		if (this._volume <= this._volume_min) {
			this._volume = this._volume_min;
		}
		a.cookie("volume", this._volume);
		var c = this._volume / this._volume_step;
		var b = "";
		switch (c) {
		  case 1:
			b = "\u5df2\u51cf\u81f3\u6700\u5c0f";
			break;
		  case 2:
			b = "\u51cf\u5c11\u4e3a\u4e8c";
			break;
		  case 3:
			b = "\u51cf\u5c11\u4e3a\u4e09";
			break;
		  case 4:
			b = "\u51cf\u5c11\u4e3a\u56db";
			break;
		  case 5:
			b = "\u51cf\u5c11\u4e3a\u4e94";
			break;
		}
		this._tool_play("amblyopia/_volume_sub_" + c + ".mp3");
		this._tooltips("\u97f3\u91cf" + b);
	}, _speed_add:function () {
		if (a.cookie("sound") != "true") {
			return;
		}
		this._speed = Number(a.cookie("speed")) || this._speed;
		this._speed += this._speed_step;
		if (this._speed >= this._speed_max) {
			this._speed = this._speed_max;
		}
		a.cookie("speed", this._speed);
		var b = (this._speed + this._speed_max) / this._speed_step + 1;
		var c = "";
		switch (b) {
		  case 1:
			c = "\u589e\u52a0\u4e3a\u4e00";
			break;
		  case 2:
			c = "\u589e\u52a0\u4e3a\u4e8c";
			break;
		  case 3:
			c = "\u589e\u81f3\u6700\u5feb";
			break;
		}
		this._tool_play("amblyopia/_speed_add_" + b + ".mp3");
		this._tooltips("\u8bed\u901f" + c);
	}, _speed_sub:function () {
		if (a.cookie("sound") != "true") {
			return;
		}
		this._speed = Number(a.cookie("speed")) || this._speed;
		this._speed -= this._speed_step;
		if (this._speed <= this._speed_min) {
			this._speed = this._speed_min;
		}
		a.cookie("speed", this._speed);
		var b = (this._speed + this._speed_max) / this._speed_step + 1;
		var c = "";
		switch (b) {
		  case 1:
			c = "\u51cf\u81f3\u6700\u6162";
			break;
		  case 2:
			c = "\u51cf\u5c11\u4e3a\u4e8c";
			break;
		  case 3:
			c = "\u51cf\u5c11\u4e3a\u4e09";
			break;
		}
		this._tool_play("amblyopia/_speed_sub_" + b + ".mp3");
		this._tooltips("\u8bed\u901f" + c);
	}, _pauseAll:function () {
		soundManager.pauseAll();
	}, _resumeAll:function () {
		soundManager.resumeAll();
	}, _break_over:function () {
		if (this.tagName == Amblyopia.fn._tagName.toUpperCase() && this.getAttribute("class") != Amblyopia.fn._tagClass) {
			return;
		}
		var b = a(this);
		var c = Amblyopia.fn.handleNode(this, true);
		if (!c) {
			c = b.text();
		}
		if (c) {
			Amblyopia.fn._nodeText.push(c);
		}
		switch (this.tagName) {
		  case "SELECT":
			break;
		  case "IMG":
			b.css("border", "2px solid " + Amblyopia.fn._break_bgcolor);
			break;
		  default:
			Amblyopia.fn._textColor = this.style.color;
			Amblyopia.fn._backColor = this.style.background;
			b.css({color:Amblyopia.fn._break_color, background:Amblyopia.fn._break_bgcolor, cursor:"default"});
		}
		Amblyopia.fn._tooltips(c);
		if (this.tagName == "A" && (this.href != "#" || this.href.toLowerCase().indexOf("javascript") == -1) && this.href != "") {
			Amblyopia.fn._href = this.href;
		}
		b.focus();
	}, _break_out:function () {
		var b = a(this);
		switch (this.tagName) {
		  case "SELECT":
			break;
		  case "IMG":
			b.css("border", "");
			break;
		  default:
			b.css({color:Amblyopia.fn._textColor, background:Amblyopia.fn._backColor, cursor:""});
		}
		Amblyopia.fn._href = "";
		Amblyopia.fn._clearAll();
	}, _over_object:function () {
		var b = a(this);
		var c = Amblyopia.fn.handleNode(b.children("object").get(0), true);
		if (!c) {
			c = b.text();
		}
		if (c) {
			Amblyopia.fn._nodeText.push(c);
		}
		Amblyopia.fn._tooltips(c);
	}, _out_object:function () {
		Amblyopia.fn._clearAll();
	}, _getData:function (c, d) {
		var b = "";
		a.ajax({url:Amblyopia.fn._dataUrl, data:{s:c.s, speed:c.speed, domain:Amblyopia.fn._domain || ""}, dataType:"jsonp", jsonp:"callback", success:function (e) {
			if (e && e.success && e.msg != "") {
				b = Amblyopia.fn._dataMp3 + e.msg;
			} else {
				b = Amblyopia.fn._mp3 + "amblyopia/error.mp3";
			}
			Amblyopia.fn._play(c.s, b, true, false, d);
		}, error:function () {
		}});
	}, _un_break:function () {
		clearInterval(this._breakInterval);
		this._clearAll();
		a("#frame-main").contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (b) {
			return a("img", this).length == 0;
		}).unbind(".Break").end().end().find("object").parent().unbind(".Break").end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).unbind(".Break").end().end().end().find("frame, iframe").filter(function (b) {
			return Amblyopia.fn._isThisHref(a(this).attr("src"));
		}).contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (b) {
			return a("img", this).length == 0;
		}).unbind(".Break").end().end().find("object").parent().unbind(".Break").end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).unbind(".Break");
	}, _un_repeat:function () {
		if (this._repeatIndex) {
			var b = this._repeatNodes[this._repeatIndex];
			if (b.tagName == "A") {
				b.style.color = this._readed_linkcolor;
			} else {
				if (b.tagName == "IMG") {
					b.style.border = "";
				} else {
					b.style.color = this._readed_fontcolor;
				}
			}
			b.style.background = this._readed_bgcolor;
		}
		this._repeatFlag = false;
		this._repeatIndex = 0;
		this._repeatNodes = [];
		clearInterval(this._repeatInterval);
		this._repeatNode = "";
		this._repeatNum = 0;
		a("#frame-main").contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").unbind(".Repeat").end().find("frame, iframe").filter(function (c) {
			return Amblyopia.fn._isThisHref(a(this).attr("src"));
		}).contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").unbind(".Repeat");
	}, _break_open:function () {
		if (a.cookie("sound") != "true") {
			return;
		}
		this._clearAll();
		this._un_repeat();
		this._breakRead();
	}, _bind:function () {
		a("#frame-main").contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (b) {
			return a("img", this).length == 0;
		}).bind("mouseenter.Break", this._break_over).bind("mouseleave.Break", this._break_out).end().end().find("object").parent().bind("mouseenter.Break", this._over_object).bind("mouseleave.Break", this._out_object).end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).bind("mouseenter.Break", this._over_object).bind("mouseleave.Break", this._out_object).end().end().end().find("frame, iframe").filter(function (b) {
			return Amblyopia.fn._isThisHref(a(this).attr("src"));
		}).contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (b) {
			return a("img", this).length == 0;
		}).bind("mouseenter.Break", this._break_over).bind("mouseleave.Break", this._break_out).end().end().find("object").parent().bind("mouseenter.Break", this._over_object).bind("mouseleave.Break", this._out_object).end().end().find("embed[id!=sm2movie]").parent().filter(function () {
			return this.tagName != "OBJECT";
		}).bind("mouseenter.Break", this._over_object).bind("mouseleave.Break", this._out_object);
	}, _breakRead:function () {
		this._bind();
		this._readArray();
	}, _readArray:function () {
		this._breakInterval = setInterval(function () {
			if (Amblyopia.fn._nodeText.length > 0) {
				var b = Amblyopia.fn._nodeText.pop();
				Amblyopia.fn._readyToPlay(b, false, function () {
					Amblyopia.fn._clearAll();
				});
			}
		}, this._breakTimer);
	}, _repeat_open:function () {
		if (a.cookie("sound") != "true" || a.cookie("pause")) {
			return;
		}
		this._clearAll();
		this._un_break();
		this._repeat_ready();
	}, _repeat_ready:function () {
		this._clearAll();
		this._repeatIndex = 0;
		this._repeatFlag = true;
		this._repeatNodes = [];
		this._repeatRead();
	}, _getRepeatNodes:function () {
		this._repeatNodes = this._nodes;
	}, _repeatRead:function () {
		this._read_color();
		this._getRepeatNodes();
		a("#frame-main").contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (b) {
			return a("img, textarea, select", this).length == 0;
		}).bind("mouseenter.Repeat", this._repeat_over).bind("mouseleave.Repeat", this._repeat_out).end().end().find("frame, iframe").filter(function (b) {
			return Amblyopia.fn._isThisHref(a(this).attr("src"));
		}).contents().find("a, img, button, input, " + this._tagName.toUpperCase() + ", select, textarea").filter(function (b) {
			return a("img, textarea, select", this).length == 0;
		}).bind("mouseenter.Repeat", this._repeat_over).bind("mouseleave.Repeat", this._repeat_out);
		this._repeatIntervalRead();
	}, _repeat_over:function () {
		if (this.tagName == Amblyopia.fn._tagName.toUpperCase() && this.getAttribute("class") != Amblyopia.fn._tagClass) {
			return;
		}
		this._runTwice = true;
		if (a.inArray(this.tagName, Amblyopia.fn._repeatTagNames) == -1) {
			return;
		}
		Amblyopia.fn._repeatNode = this;
	}, _repeat_out:function () {
		Amblyopia.fn._repeatNode = "";
	}, _repeatIntervalRead:function () {
		var b = this;
		this._repeatInterval = setInterval(function () {
			if (!b._repeatFlag) {
				return;
			}
			if (b._runTwice) {
				b._runTwice = false;
			} else {
				b._runTwice = true;
			}
			if (typeof b._repeatNode == "object" && b._runTwice) {
				var c = b._getRepeatIndex(b._repeatNode);
				if (c) {
					b._repeatNum++;
					var d = b._repeatNodes[b._repeatIndex];
					if (d.tagName == "A") {
						d.style.color = b._readed_linkcolor;
					} else {
						if (d.tagName == "IMG") {
							d.style.border = "";
						} else {
							d.style.color = b._readed_fontcolor;
						}
					}
					b._href = "";
					b._repeatNodes[b._repeatIndex].style.background = b._readed_bgcolor;
					b._repeat_reset();
					b._repeatIndex = c;
					b._repeat_start();
				}
				b._repeatNode = "";
			}
		}, this._repeatTimer);
	}, _repeat_reset:function () {
		this._queue = [];
		this._req = true;
	}, _repeat_start:function () {
		if (this._repeatNum == 0) {
			return;
		}
		var b = this._repeatNodes[this._repeatIndex];
		if (b == undefined || this._repeatIndex >= this._repeatNodes.length) {
			if (this._repeatIndex >= this._repeatNodes.length) {
				this._repeatIndex--;
			}
			return;
		}
		if (!this.isVisible(b)) {
			this._repeatIndex++;
			this._repeat_start();
			return;
		}
		if (!this._repeatFlag) {
			this._repeatFlag = false;
			return;
		}
		this._scroll(b);
		var d = this.handleNode(b, true);
		if (!d) {
			d = b.innerText == undefined ? b.textContent : b.innerText;
		}
		var c = this._chr(d);
		if (c) {
			if (b.tagName == "IMG") {
				b.style.border = "2px solid " + this._repeat_bgcolor;
			} else {
				b.style.color = this._repeat_color;
				b.style.background = this._repeat_bgcolor;
			}
			if (b.tagName == "A" && (b.href != "#" || b.href.toLowerCase().indexOf("javascript") == -1) && b.href != "") {
				this._href = b.href;
			}
			a(b).focus();
			this._tooltips(d);
			if (this._req) {
				this._readyToPlay(c, false, "callback");
				this._req = false;
			}
			this._repeat_play(b);
		} else {
			this._repeatIndex++;
			this._repeat_start();
		}
	}, _repeat_play:function (b) {
		var d = this, c = this._queue.shift();
		if (c) {
			this._clearAll();
			c.play({onfinish:function () {
				d._stopAll();
				d._repeatIndex++;
				if (b.tagName == "A") {
					b.style.color = d._readed_linkcolor;
				} else {
					if (b.tagName == "IMG") {
						b.style.border = "";
					} else {
						b.style.color = d._readed_fontcolor;
					}
				}
				d._href = "";
				b.style.background = d._readed_bgcolor;
				d._repeat_start();
			}, onload:function () {
				if (d._repeatNum == 0) {
					return;
				}
				var f = d._repeatIndex;
				var e = d._repeatNodes[f + 1];
				while (!d.isVisible(e)) {
					f++;
					e = d._repeatNodes[f];
				}
				if (!e || f >= d._repeatNodes.length - 1) {
					return;
				}
				if (!d._repeatFlag) {
					d._repeatFlag = false;
					return;
				}
				var g = d.handleNode(e, true);
				if (!g) {
					g = e.innerText == undefined ? e.textContent : e.innerText;
				}
				g = d._chr(g);
				d._readyToPlay(g, false, "callback");
			}});
		} else {
			setTimeout(function () {
				d._repeat_play(b);
			}, 100);
		}
	}, _readyToPlay:function (g, c, h) {
		g = this._chr(g);
		if (g == "") {
			return false;
		}
		var d = g;
		var f = a.cookie("speed") || this._speed;
		var e = {s:encodeURIComponent(d), speed:f};
		if (c) {
			var d = Amblyopia.fn._mp3 + d;
			Amblyopia.fn._play(g, d, true, false, h);
		} else {
			this._getData(e, h);
		}
	}, _scroll:function (f) {
		var i = a("#frame-main");
		var e = a(f).offset().top;
		var h = f.ownerDocument;
		var g = h.parentWindow || h.defaultView;
		if (g != window.frames["frame-main"]) {
			e += g.frameElement.offsetTop;
		}
		var d = i.get(0).contentWindow.document.documentElement.scrollTop;
		var j = i.height();
		if (e - d > j / 2 || d > e) {
			i.get(0).contentWindow.document.documentElement.scrollTop = (e - j / 2);
		}
	}, _getRepeatIndex:function (e) {
		var c = 0;
		for (var d = 0, b = this._repeatNodes.length; d < b; d++) {
			if (this._repeatNodes[d] == e) {
				c = d;
				break;
			}
		}
		return c;
	}, _sound_open:function () {
		a("#x-sound").addClass("btn-ico-active").find("ins").removeClass("x_sound_no").addClass("x_sound");
		var b = a.cookie("read");
		a("#x-break, #x-repeat").removeClass("btn-ico-active");
		switch (b) {
		  case "repeat":
			a("#x-repeat").addClass("btn-ico-active").find("ins").removeClass("x_repeat x_play").addClass("x_pause");
			this._repeat_open();
			break;
		  case "break":
		  default:
			a("#x-break").addClass("btn-ico-active");
			this._break_open();
		}
	}, _sound_close:function () {
		a("#x-sound").removeClass("btn-ico-active").find("ins").removeClass("x_sound").addClass("x_sound_no");
		a("#x-break, #x-repeat").removeClass("btn-ico-active");
		a("#x-repeat").find("ins").removeClass("x_pause x_play").addClass("x_repeat");
		this._un_break();
		this._un_repeat();
	}, _soundMore:function () {
		if (a.cookie("soundMange")) {
			a.cookie("soundMange", null);
			this._close_sound_panel();
		} else {
			a.cookie("soundMange", "true");
			this._show_sound_panel();
		}
	}, _show_sound_panel:function () {
		var b = a("#x-sound-panel"), c = a("#x-sound").offset();
		b.css("left", c.left).slideDown("slow", function () {
			var f = a(this), d = f.outerWidth(true), e = f.outerHeight(true);
			if (a.browser.msie) {
				a(".bg-iframe").css({left:c.left, width:d, height:e}).show();
			}
		});
		a("#x-sound-more").addClass("btn-ico-active");
		this._tool_play("amblyopia/_sound_panel_open.mp3");
	}, _close_sound_panel:function (c) {
		a(".bg-iframe").hide();
		a("#x-sound-panel").slideUp("slow");
		a("#x-sound-more").removeClass("btn-ico-active");
		this._tool_play("amblyopia/_sound_panel_close.mp3");
	}, _getNodes:function (b) {
		if (b.tagName == "undefined") {
			return;
		}
		if (b.tagName == Amblyopia.fn._tagName.toUpperCase() && b.getAttribute("class") != Amblyopia.fn._tagClass) {
			return;
		}
		if (a.inArray(b.tagName, Amblyopia.fn._repeatTagNames) == -1) {
			return;
		}
		if (b.style.display == "none") {
			return;
		}
		if (b.tagName == "IMG" && (b.width < 50 || b.height < 50)) {
			return;
		}
		var c = Amblyopia.fn.handleNode(b, true);
		if (!c) {
			c = b.innerText == undefined ? b.textContent : b.innerText;
		}
		c = Amblyopia.fn._chr(c);
		if (!c) {
			return;
		}
		Amblyopia.fn._nodes.push(b);
	}, _bind_keyBoard:function (h) {
		var i = document.getElementById("frame-main"), g = i.contentWindow;
		var h = h || g.event;
		var b = h.which || h.keyCode;
		if (h.shiftKey) {
			if (b == 13) {
				if (a.cookie("sound") != "true") {
					a("#x-sound").trigger("mousedown");
					Amblyopia.fn._tool_play("amblyopia/_amblyopia.mp3");
				}
			}
		} else {
			switch (b) {
			  case 13:
				Amblyopia.fn._open_url(Amblyopia.fn._href);
				break;
			  case 32:
				h.preventDefault ? h.preventDefault() : h.returnValue = false;
				var f = a.cookie("sound"), c = a.cookie("read");
				if (f != "true" || c != "repeat") {
					return;
				}
				var d = a("#x-repeat ins");
				d.removeClass("x_repeat");
				if (d.hasClass("x_pause")) {
					d.removeClass("x_pause").addClass("x_play");
					Amblyopia.fn._pause_start();
					Amblyopia.fn._tool_play("amblyopia/_pause.mp3");
				} else {
					if (d.hasClass("x_play")) {
						d.removeClass("x_play").addClass("x_pause");
						Amblyopia.fn._pause_start();
					}
				}
				break;
			}
		}
	}, _keyBoard:function () {
		a("body").focus();
		this._nodes = [];
		a(document).unbind("keydown", this._bind_keyBoard);
		a(document).bind("keydown", this._bind_keyBoard);
		var d = document.getElementById("frame-main"), c = d.contentWindow, b = c.document;
		b.documentElement.onkeydown = this._bind_keyBoard;
		this._dom(b ? b.body : d, this._getNodes, function (e) {
			return Amblyopia.fn._leafNode(e);
		}, 0);
	}, _tool_play:function (d, c, e) {
		if (!e || typeof e != "function") {
			if (a.cookie("read") == "repeat") {
				e = function () {
					Amblyopia.fn._repeat_reset();
					Amblyopia.fn._repeat_start();
				};
			} else {
				e = function () {
				};
			}
		}
		c = c == null ? true : c;
		if (a.cookie("sound") == "true" || this._tool_tips) {
			this._clearAll();
			this._readyToPlay(d, c, e);
		}
	}, _queryIframe:function () {
		if (typeof this.cookieEnabled == "undefined") {
			this.cookieEnabled = this.isCookieEnabled();
		}
		if (!this.cookieEnabled) {
			return;
		}
		var c = document.getElementById("frame-main").contentWindow;
		this._domain = c.location.host;
		this._frameDepth = 0;
		this._clearAll();
		this._record();
		this._setColor();
		this._ready();
		this._keyBoard();
		this._remove();
		this._getCookieIframe();
		a("#frame-main").contents().find("body").bind("mousedown", this._pageClick);
		var b = true;
		soundManager.onready(function (d) {
			if (d.success) {
				if (b) {
					Amblyopia.fn._report();
					b = false;
				}
			}
		});
	}, _getCookieIframe:function () {
		this._changeHistory();
		if (a.cookie("tips")) {
			this.tips_open();
		}
		if (a.cookie("view")) {
			a("#x-view").attr("title", "\u89c6\u56fe\u6a21\u5f0f").find("ins").removeClass("x_view_text").addClass("x_view_data");
			this._view_text();
		}
		if (a.cookie("font")) {
			this._changeFont();
		}
		if (a.cookie("state") && a.cookie("sound") != "true") {
			this._on_state();
		}
		if (a.cookie("zoom")) {
			var b = a.cookie("zoom");
			this._activeZoom(b);
			this._zoom(b);
		}
		if (!a.cookie("sound") && this._isRead) {
			a.cookie("sound", "true");
		}
		if (a.cookie("sound") == "true") {
			this._sound_open();
		}
	}});
})(jQuery);

