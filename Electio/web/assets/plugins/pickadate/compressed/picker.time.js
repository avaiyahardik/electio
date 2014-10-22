/*!
 * Time picker for pickadate.js v3.3.2
 * http://amsul.github.io/pickadate.js/time.htm
 */
!function(a) {
    "function" == typeof define && define.amd ? define(["picker", "jquery"], a) : a(Picker, jQuery)
}(function(a, b) {
    function c(a, b) {
        var c = this, d = a.$node.data("value");
        c.settings = b, c.queue = {interval: "i", min: "measure create", max: "measure create", now: "now create", select: "parse create validate", highlight: "create validate", view: "create validate", disable: "flipItem", enable: "flipItem"}, c.item = {}, c.item.interval = b.interval || 30, c.item.disable = (b.disable || []).slice(0), c.item.enable = -function(a) {
            return a[0] === !0 ? a.shift() : -1
        }(c.item.disable), c.set("min", b.min).set("max", b.max).set("now").set("select", d || a.$node[0].value || c.item.min, {format: d ? b.formatSubmit : b.format}), c.key = {40: 1, 38: -1, 39: 1, 37: -1, go: function(a) {
                c.set("highlight", c.item.highlight.pick + a * c.item.interval, {interval: a * c.item.interval}), this.render()
            }}, a.on("render", function() {
            var c = a.$root.children(), d = c.find("." + b.klass.viewset);
            d.length && (c[0].scrollTop = ~~d.position().top - 2 * d[0].clientHeight)
        }).on("open", function() {
            a.$root.find("button").attr("disable", !1)
        }).on("close", function() {
            a.$root.find("button").attr("disable", !0)
        })
    }
    var d = 24, e = 60, f = 12, g = d * e;
    c.prototype.set = function(a, b, c) {
        var d = this;
        return d.item["enable" == a ? "disable" : "flip" == a ? "enable" : a] = d.queue[a].split(" ").map(function(e) {
            return b = d[e](a, b, c)
        }).pop(), "select" == a ? d.set("highlight", d.item.select, c) : "highlight" == a ? d.set("view", d.item.highlight, c) : "interval" == a ? d.set("min", d.item.min, c).set("max", d.item.max, c) : ("flip" == a || "min" == a || "max" == a || "disable" == a || "enable" == a) && d.item.select && d.item.highlight && ("min" == a && d.set("max", d.item.max, c), d.set("select", d.item.select, c).set("highlight", d.item.highlight, c)), d
    }, c.prototype.get = function(a) {
        return this.item[a]
    }, c.prototype.create = function(c, f, h) {
        var i = this;
        return f = void 0 === f ? c : f, a._.isDate(f) && (f = [f.getHours(), f.getMinutes()]), b.isPlainObject(f) && a._.isInteger(f.pick) ? f = f.pick : b.isArray(f) ? f = +f[0] * e + +f[1] : a._.isInteger(f) || (f = i.now(c, f, h)), "max" == c && f < i.item.min.pick && (f += g), "min" != c && "max" != c && (f - i.item.min.pick) % i.item.interval !== 0 && (f += i.item.interval), f = i.normalize(c, f, h), {hour: ~~(d + f / e) % d, mins: (e + f % e) % e, time: (g + f) % g, pick: f}
    }, c.prototype.now = function(b, c) {
        var d = new Date, f = d.getHours() * e + d.getMinutes();
        return f -= f % this.item.interval, a._.isInteger(c) ? c += "min" == b && 0 > c && 0 === f ? 2 : 1 : c = 1, c * this.item.interval + f
    }, c.prototype.normalize = function(a, b) {
        var c = this.item.interval, d = "min" == a ? 0 : (b - this.item.min.pick) % c;
        return b - (d + (0 > b ? c : 0))
    }, c.prototype.measure = function(c, f, g) {
        var h = this;
        return f ? f === !0 || a._.isInteger(f) ? f = h.now(c, f, g) : b.isPlainObject(f) && a._.isInteger(f.pick) && (f = h.normalize(c, f.pick, g)) : f = "min" == c ? [0, 0] : [d - 1, e - 1], f
    }, c.prototype.validate = function(a, b, c) {
        var d = this, e = c && c.interval ? c.interval : d.item.interval;
        return d.disabled(b) && (b = d.shift(b, e)), b = d.scope(b), d.disabled(b) && (b = d.shift(b, -1 * e)), b
    }, c.prototype.disabled = function(c) {
        var d = this, e = d.item.disable.filter(function(e) {
            return a._.isInteger(e) ? c.hour == e : b.isArray(e) || a._.isDate(e) ? c.pick == d.create(e).pick : void 0
        });
        return e = e.length && !e.filter(function(a) {
            return b.isArray(a) && "inverted" == a[2]
        }).length, -1 === d.item.enable ? !e : e || c.pick < d.item.min.pick || c.pick > d.item.max.pick
    }, c.prototype.shift = function(a, b) {
        var c = this, d = c.item.min.pick, e = c.item.max.pick;
        for (b = b || c.item.interval; c.disabled(a) && (a = c.create(a.pick += b), !(a.pick <= d || a.pick >= e)); )
            ;
        return a
    }, c.prototype.scope = function(a) {
        var b = this.item.min.pick, c = this.item.max.pick;
        return this.create(a.pick > c ? c : a.pick < b ? b : a)
    }, c.prototype.parse = function(c, d, f) {
        var g = this, h = {};
        if (!d || a._.isInteger(d) || b.isArray(d) || a._.isDate(d) || b.isPlainObject(d) && a._.isInteger(d.pick))
            return d;
        if (!f || !f.format)
            throw"Need a formatting option to parse this..";
        return g.formats.toArray(f.format).map(function(b) {
            var c = g.formats[b], e = c ? a._.trigger(c, g, [d, h]) : b.replace(/^!/, "").length;
            c && (h[b] = d.substr(0, e)), d = d.substr(e)
        }), +h.i + e * (+(h.H || h.HH) || +(h.h || h.hh) % 12 + (/^p/i.test(h.A || h.a) ? 12 : 0))
    }, c.prototype.formats = {h: function(b, c) {
            return b ? a._.digits(b) : c.hour % f || f
        }, hh: function(b, c) {
            return b ? 2 : a._.lead(c.hour % f || f)
        }, H: function(b, c) {
            return b ? a._.digits(b) : "" + c.hour % 24
        }, HH: function(b, c) {
            return b ? a._.digits(b) : a._.lead(c.hour % 24)
        }, i: function(b, c) {
            return b ? 2 : a._.lead(c.mins)
        }, a: function(a, b) {
            return a ? 4 : g / 2 > b.time % g ? "a.m." : "p.m."
        }, A: function(a, b) {
            return a ? 2 : g / 2 > b.time % g ? "AM" : "PM"
        }, toArray: function(a) {
            return a.split(/(h{1,2}|H{1,2}|i|a|A|!.)/g)
        }, toString: function(b, c) {
            var d = this;
            return d.formats.toArray(b).map(function(b) {
                return a._.trigger(d.formats[b], d, [0, c]) || b.replace(/^!/, "")
            }).join("")
        }}, c.prototype.flipItem = function(a, c) {
        var d = this, e = d.item.disable, f = -1 === d.item.enable;
        return"flip" == c ? d.item.enable = f ? 1 : -1 : "enable" == a && c === !0 || "disable" == a && c === !1 ? (d.item.enable = 1, e = []) : "enable" == a && c === !1 || "disable" == a && c === !0 ? (d.item.enable = -1, e = []) : b.isArray(c) && (f && "enable" == a || !f && "disable" == a ? e = d.addDisabled(e, c) : f || "enable" != a ? f && "disable" == a && (e = d.removeDisabled(e, c)) : e = d.addEnabled(e, c)), e
    }, c.prototype.addEnabled = function(c, d) {
        var e = this;
        return d.map(function(d) {
            e.filterDisabled(c, d, 1).length && (c = e.removeDisabled(c, [d]), b.isArray(d) && c.filter(function(b) {
                return a._.isInteger(b) && e.create(d).hour === b
            }).length && (d = d.slice(0), d.push("inverted"), c.push(d)))
        }), c
    }, c.prototype.addDisabled = function(a, c) {
        var d = this;
        return c.map(function(c) {
            d.filterDisabled(a, c).length ? b.isArray(c) && d.filterDisabled(a, c, 1).length && (a = d.removeDisabled(a, [c])) : a.push(c)
        }), a
    }, c.prototype.removeDisabled = function(a, b) {
        var c = this;
        return b.map(function(b) {
            a = c.filterDisabled(a, b, 1)
        }), a
    }, c.prototype.filterDisabled = function(a, c, d) {
        var e = b.isArray(c);
        return a.filter(function(a) {
            var f = !e && c === a || e && b.isArray(a) && c.toString() === a.toString();
            return d ? !f : f
        })
    }, c.prototype.i = function(b, c) {
        return a._.isInteger(c) && c > 0 ? c : this.item.interval
    }, c.prototype.nodes = function(b) {
        var c = this, d = c.settings, e = c.item.select, f = c.item.highlight, g = c.item.view, h = c.item.disable;
        return a._.node("ul", a._.group({min: c.item.min.pick, max: c.item.max.pick, i: c.item.interval, node: "li", item: function(b) {
                return b = c.create(b), [a._.trigger(c.formats.toString, c, [a._.trigger(d.formatLabel, c, [b]) || d.format, b]), function(a, i) {
                        return e && e.pick == i && a.push(d.klass.selected), f && f.pick == i && a.push(d.klass.highlighted), g && g.pick == i && a.push(d.klass.viewset), h && c.disabled(b) && a.push(d.klass.disabled), a.join(" ")
                    }([d.klass.listItem], b.pick), "data-pick=" + b.pick]
            }}) + a._.node("li", a._.node("button", d.clear, d.klass.buttonClear, "type=button data-clear=1" + (b ? "" : " disable"))), d.klass.list)
    }, c.defaults = function(a) {
        return{clear: "Clear", format: "h:i A", interval: 30, klass: {picker: a + " " + a + "--time", holder: a + "__holder", list: a + "__list", listItem: a + "__list-item", disabled: a + "__list-item--disabled", selected: a + "__list-item--selected", highlighted: a + "__list-item--highlighted", viewset: a + "__list-item--viewset", now: a + "__list-item--now", buttonClear: a + "__button--clear"}}
    }(a.klasses().picker), a.extend("pickatime", c)
});