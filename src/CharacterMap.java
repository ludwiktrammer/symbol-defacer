/*
The MIT License

Copyright (c) 2012 Ludwik Trammer <ludwik at trammer.pl>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

import java.util.HashMap;
import java.util.Map;


public class CharacterMap {
	
	/* This array maps <font face="Symbol"> character codes
	 * to codes of their Unicode counterparts.
	 * It's based on information from
	 * http://www.alanwood.net/demos/symbol.html
	 */
	private static final Character[][] characterArray = {
		{180, 215},
		{184, 247},
		{210, 174},
		{211, 169},
		{216, 172},
		{226, 174},
		{227, 169},
		{166, 402},
		{'A', 913},
		{'B', 914},
		{'G', 915},
		{'D', 916},
		{'E', 917},
		{'Z', 918},
		{'H', 919},
		{'Q', 920},
		{'I', 921},
		{'K', 922},
		{'L', 923},
		{'M', 924},
		{'N', 925},
		{'X', 926},
		{'O', 927},
		{'P', 928},
		{'R', 929},
		{'S', 931},
		{'T', 932},
		{'U', 933},
		{'F', 934},
		{'C', 935},
		{'Y', 936},
		{'W', 937},
		{'a', 945},
		{'b', 946},
		{'g', 947},
		{'d', 948},
		{'e', 949},
		{'z', 950},
		{'h', 951},
		{'q', 952},
		{'i', 953},
		{'k', 954},
		{'l', 955},
		{'m', 956},
		{'n', 957},
		{'x', 958},
		{'o', 959},
		{'p', 960},
		{'r', 961},
		{'V', 962},
		{'s', 963},
		{'t', 964},
		{'u', 965},
		{'f', 966},
		{'c', 967},
		{'y', 968},
		{'w', 969},
		{'J', 977},
		{'j', 981},
		{'v', 982},
		{161, 978},
		{162, 8242},
		{164, 8260},
		{178, 8243},
		{188, 8230},
		{192, 8501},
		{193, 8465},
		{194, 8476},
		{195, 8472},
		{212, 8482},
		{228, 8482},
		{240, 8364},
		{171, 8596},
		{172, 8592},
		{173, 8593},
		{174, 8594},
		{175, 8595},
		{191, 8629},
		{219, 8660},
		{220, 8656},
		{221, 8657},
		{222, 8658},
		{223, 8659},
		{34, 8704},
		{36, 8707},
		{39, 8717},
		{42, 8727},
		{45, 8722},
		{64, 8773},
		{92, 8756},
		{94, 8869},
		{126, 8764},
		{163, 8804},
		{165, 8734},
		{179, 8805},
		{181, 8733},
		{182, 8706},
		{183, 8729},
		{185, 8800},
		{186, 8801},
		{187, 8776},
		{196, 8855},
		{197, 8853},
		{198, 8709},
		{199, 8745},
		{200, 8746},
		{201, 8835},
		{202, 8839},
		{203, 8836},
		{204, 8834},
		{205, 8838},
		{206, 8712},
		{207, 8713},
		{208, 8736},
		{209, 8711},
		{213, 8719},
		{214, 8730},
		{215, 8901},
		{217, 8743},
		{218, 8744},
		{229, 8721},
		{242, 242},
		{189, 9168},
		{190, 9135},
		{225, 9001},
		{230, 9115},
		{231, 9116},
		{232, 9117},
		{233, 9121},
		{234, 9122},
		{235, 9123},
		{236, 9127},
		{237, 9128},
		{238, 9129},
		{239, 9130},
		{241, 9002},
		{243, 8992},
		{244, 9134},
		{245, 8993},
		{246, 9118},
		{247, 9119},
		{248, 9120},
		{249, 9124},
		{250, 9125},
		{251, 9126},
		{252, 9131},
		{253, 9132},
		{254, 9133},
		{224, 9674},
		{167, 9827},
		{168, 9830},
		{169, 9829},
		{170, 9824}
	};
	
	/* I wont this information inside a Map object! */
	public static final Map<Character, Character> map;
	
	static {
		map =  new HashMap<Character, Character>(characterArray.length);
		for (Character[] subArray : characterArray) {
			map.put(subArray[0], subArray[1]);
        }
	}
	
}
