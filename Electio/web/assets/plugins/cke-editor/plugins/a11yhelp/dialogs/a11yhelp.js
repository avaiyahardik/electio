﻿/*
 Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.md or http://ckeditor.com/license
 */
        CKEDITOR.dialog.add("a11yHelp", function(j) {
            var a = j.lang.a11yhelp, l = CKEDITOR.tools.getNextId(), e = {8: a.backspace, 9: a.tab, 13: a.enter, 16: a.shift, 17: a.ctrl, 18: a.alt, 19: a.pause, 20: a.capslock, 27: a.escape, 33: a.pageUp, 34: a.pageDown, 35: a.end, 36: a.home, 37: a.leftArrow, 38: a.upArrow, 39: a.rightArrow, 40: a.downArrow, 45: a.insert, 46: a["delete"], 91: a.leftWindowKey, 92: a.rightWindowKey, 93: a.selectKey, 96: a.numpad0, 97: a.numpad1, 98: a.numpad2, 99: a.numpad3, 100: a.numpad4, 101: a.numpad5, 102: a.numpad6, 103: a.numpad7, 104: a.numpad8,
                105: a.numpad9, 106: a.multiply, 107: a.add, 109: a.subtract, 110: a.decimalPoint, 111: a.divide, 112: a.f1, 113: a.f2, 114: a.f3, 115: a.f4, 116: a.f5, 117: a.f6, 118: a.f7, 119: a.f8, 120: a.f9, 121: a.f10, 122: a.f11, 123: a.f12, 144: a.numLock, 145: a.scrollLock, 186: a.semiColon, 187: a.equalSign, 188: a.comma, 189: a.dash, 190: a.period, 191: a.forwardSlash, 192: a.graveAccent, 219: a.openBracket, 220: a.backSlash, 221: a.closeBracket, 222: a.singleQuote};
            e[CKEDITOR.ALT] = a.alt;
            e[CKEDITOR.SHIFT] = a.shift;
            e[CKEDITOR.CTRL] = a.ctrl;
            var f = [CKEDITOR.ALT, CKEDITOR.SHIFT,
                CKEDITOR.CTRL], m = /\$\{(.*?)\}/g, p = function() {
                var a = j.keystrokeHandler.keystrokes, g = {}, c;
                for (c in a)
                    g[a[c]] = c;
                return function(a, c) {
                    var b;
                    if (g[c]) {
                        b = g[c];
                        for (var h, i, k = [], d = 0; d < f.length; d++)
                            i = f[d], h = b / f[d], 1 < h && 2 >= h && (b -= i, k.push(e[i]));
                        k.push(e[b] || String.fromCharCode(b));
                        b = k.join("+")
                    } else
                        b = a;
                    return b
                }
            }();
            return{title: a.title, minWidth: 600, minHeight: 400, contents: [{id: "info", label: j.lang.common.generalTab, expand: !0, elements: [{type: "html", id: "legends", style: "white-space:normal;", focus: function() {
                                    this.getElement().focus()
                                },
                                html: function() {
                                    for (var e = '<div class="cke_accessibility_legend" role="document" aria-labelledby="' + l + '_arialbl" tabIndex="-1">%1</div><span id="' + l + '_arialbl" class="cke_voice_label">' + a.contents + " </span>", g = [], c = a.legend, j = c.length, f = 0; f < j; f++) {
                                        for (var b = c[f], h = [], i = b.items, k = i.length, d = 0; d < k; d++) {
                                            var n = i[d], o = n.legend.replace(m, p);
                                            o.match(m) || h.push("<dt>%1</dt><dd>%2</dd>".replace("%1", n.name).replace("%2", o))
                                        }
                                        g.push("<h1>%1</h1><dl>%2</dl>".replace("%1", b.name).replace("%2", h.join("")))
                                    }
                                    return e.replace("%1",
                                            g.join(""))
                                }() + '<style type="text/css">.cke_accessibility_legend{width:600px;height:400px;padding-right:5px;overflow-y:auto;overflow-x:hidden;}.cke_browser_quirks .cke_accessibility_legend,.cke_browser_ie6 .cke_accessibility_legend{height:390px}.cke_accessibility_legend *{white-space:normal;}.cke_accessibility_legend h1{font-size: 20px;border-bottom: 1px solid #AAA;margin: 5px 0px 15px;}.cke_accessibility_legend dl{margin-left: 5px;}.cke_accessibility_legend dt{font-size: 13px;font-weight: bold;}.cke_accessibility_legend dd{margin:10px}</style>'}]}],
                buttons: [CKEDITOR.dialog.cancelButton]}
        });