package Hexgame;

/**
 * Created by aprile on 2015/9/25.
 */

/**
 * construct all the points used in the hexgame.
 * This is very useful in nextMove class by finding whether the legal position
 */
public class Position {
    Point[] p=new Point[217];
    private static final float SQRT3 = 1.732f;
    public Position(){
        Point p[]=new Point[217];
        p[0]=new Point(0,0,0);p[3]=new Point(3,2,0);p[11]=new Point(11,4,0);p[25]=new Point(25,6,0);p[45]=new Point(45,8,0);p[71]=new Point(71,10,0);p[103]=new Point(103,12,0);p[141]=new Point(141,14,0);p[185]=new Point(185,16,0);
        p[6]=new Point(6,-2,0);p[17]=new Point(17,-4,0);p[34]=new Point(34,-6,0);p[57]=new Point(57,-8,0);p[86]=new Point(86,-10,0);p[121]=new Point(121,-12,0);p[162]=new Point(162,-14,0);p[209]=new Point(209,-16,0);
        p[1]=new Point(1,-1,SQRT3);p[18]=new Point(18,-3,SQRT3);p[35]=new Point(35,-5,SQRT3);p[58]=new Point(58,-7,SQRT3);p[87]=new Point(87,-9,SQRT3);p[122]=new Point(122,-11,SQRT3);p[163]=new Point(163,-13,SQRT3);p[210]=new Point(210,-15,SQRT3);
        p[2]=new Point(2,1,SQRT3);p[10]=new Point(10,3,SQRT3);p[24]=new Point(24,5,SQRT3);p[44]=new Point(44,7,SQRT3);p[70]=new Point(70,9,SQRT3);p[102]=new Point(102,11,SQRT3);p[140]=new Point(140,13,SQRT3);p[184]=new Point(184,15,SQRT3);
        p[5]=new Point(5,-1,-SQRT3);p[16]=new Point(16,-3,-SQRT3);p[33]=new Point(33,-5,-SQRT3);p[56]=new Point(56,-7,-SQRT3);p[85]=new Point(85,-9,-SQRT3);p[120]=new Point(120,-11,-SQRT3);p[161]=new Point(161,-13,-SQRT3);p[208]=new Point(208,-15,-SQRT3);
        p[4]=new Point(4,1,-SQRT3);p[12]=new Point(12,3,-SQRT3);p[26]=new Point(26,5,-SQRT3);p[46]=new Point(46,7,-SQRT3);p[72]=new Point(72,9,-SQRT3);p[104]=new Point(104,11,-SQRT3);p[142]=new Point(142,13,-SQRT3);p[186]=new Point(186,15,-SQRT3);
        p[8]=new Point(8,0,2*SQRT3);p[7]=new Point(7,-2,2*SQRT3);p[36]=new Point(36,-4,2*SQRT3);p[59]=new Point(59,-6,2*SQRT3);p[88]=new Point(88,-8,2*SQRT3);p[123]=new Point(123,-10,2*SQRT3);p[164]=new Point(164,-12,2*SQRT3);p[211]=new Point(211,-14,2*SQRT3);
        p[9]=new Point(9,2,2*SQRT3);p[23]=new Point(23,4,2*SQRT3);p[43]=new Point(43,6,2*SQRT3);p[69]=new Point(69,8,2*SQRT3);p[101]=new Point(101,10,2*SQRT3);p[139]=new Point(139,12,2*SQRT3);p[183]=new Point(183,14,2*SQRT3);
        p[14]=new Point(14,0,-2*SQRT3);p[13]=new Point(13,2,-2*SQRT3);p[27]=new Point(27,4,-2*SQRT3);p[47]=new Point(47,6,-2*SQRT3);p[73]=new Point(73,8,-2*SQRT3);p[105]=new Point(105,10,-2*SQRT3);p[143]=new Point(143,12,-2*SQRT3);p[187]=new Point(187,14,-2*SQRT3);
        p[15]=new Point(15,-2,-2*SQRT3);p[32]=new Point(32,-4,-2*SQRT3);p[55]=new Point(55,-6,-2*SQRT3);p[84]=new Point(84,-8,-2*SQRT3);p[119]=new Point(119,-10,-2*SQRT3);p[160]=new Point(160,-12,-2*SQRT3);p[207]=new Point(207,-14,-2*SQRT3);
        p[20]=new Point(20,-1,3*SQRT3);p[19]=new Point(19,-3,3*SQRT3);p[60]=new Point(60,-5,3*SQRT3);p[89]=new Point(89,-7,3*SQRT3);p[124]=new Point(124,-9,3*SQRT3);p[165]=new Point(165,-11,3*SQRT3);p[212]=new Point(212,-13,3*SQRT3);
        p[21]=new Point(21,1,3*SQRT3); p[22]=new Point(22,3,3*SQRT3); p[42]=new Point(42,5,3*SQRT3); p[68]=new Point(68,7,3*SQRT3); p[100]=new Point(100,9,3*SQRT3); p[138]=new Point(138,11,3*SQRT3); p[182]=new Point(182,13,3*SQRT3);
        p[29]=new Point(29,1,-3*SQRT3);p[28]=new Point(28,3,-3*SQRT3);p[48]=new Point(48,5,-3*SQRT3);p[74]=new Point(74,7,-3*SQRT3);p[106]=new Point(106,9,-3*SQRT3);p[144]=new Point(144,11,-3*SQRT3);p[188]=new Point(188,13,-3*SQRT3);
        p[30]=new Point(30,-1,-3*SQRT3);p[31]=new Point(31,-3,-3*SQRT3);p[54]=new Point(54,-5,-3*SQRT3);p[83]=new Point(83,-7,-3*SQRT3);p[118]=new Point(118,-9,-3*SQRT3);p[159]=new Point(159,-11,-3*SQRT3);p[206]=new Point(206,-13,-3*SQRT3);
        p[39]=new Point(39,0,4*SQRT3);p[38]=new Point(38,-2,4*SQRT3);p[37]=new Point(37,-4,4*SQRT3);p[90]=new Point(90,-6,4*SQRT3);p[125]=new Point(125,-8,4*SQRT3);p[166]=new Point(166,-10,4*SQRT3);p[213]=new Point(213,-12,4*SQRT3);
        p[40]=new Point(40,2,4*SQRT3);p[41]=new Point(41,4,4*SQRT3);p[67]=new Point(67,6,4*SQRT3);p[99]=new Point(99,8,4*SQRT3);p[137]=new Point(137,10,4*SQRT3);p[181]=new Point(181,12,4*SQRT3);
        p[51]=new Point(51,0,-4*SQRT3);p[52]=new Point(52,-2,-4*SQRT3);p[53]=new Point(53,-4,-4*SQRT3);p[82]=new Point(82,-6,-4*SQRT3);p[117]=new Point(117,-8,-4*SQRT3);p[158]=new Point(158,-10,-4*SQRT3);p[205]=new Point(205,-12,-4*SQRT3);
        p[50]=new Point(50,2,-4*SQRT3);p[49]=new Point(49,4,-4*SQRT3);p[75]=new Point(75,6,-4*SQRT3);p[107]=new Point(107,8,-4*SQRT3);p[145]=new Point(145,10,-4*SQRT3);p[189]=new Point(189,12,-4*SQRT3);
        p[64]=new Point(64,1,5*SQRT3);p[65]=new Point(65,3,5*SQRT3);p[66]=new Point(66,5,5*SQRT3);p[98]=new Point(98,7,5*SQRT3);p[136]=new Point(136,9,5*SQRT3);p[180]=new Point(180,11,5*SQRT3);
        p[63]=new Point(63,-1,5*SQRT3);p[62]=new Point(62,-3,5*SQRT3);p[61]=new Point(61,-5,5*SQRT3);p[126]=new Point(126,-7,5*SQRT3);p[167]=new Point(167,-9,5*SQRT3);p[214]=new Point(214,-11,5*SQRT3);
        p[78]=new Point(78,1,-5*SQRT3);p[77]=new Point(77,3,-5*SQRT3);p[76]=new Point(76,5,-5*SQRT3);p[108]=new Point(108,7,-5*SQRT3);p[146]=new Point(146,9,-5*SQRT3);p[190]=new Point(190,11,-5*SQRT3);
        p[79]=new Point(79,-1,-5*SQRT3);p[80]=new Point(80,-3,-5*SQRT3);p[81]=new Point(81,-5,-5*SQRT3);p[116]=new Point(116,-7,-5*SQRT3);p[157]=new Point(157,-9,-5*SQRT3);p[204]=new Point(204,-11,-5*SQRT3);
        p[94]=new Point(94,0,6*SQRT3);p[95]=new Point(95,2,6*SQRT3);p[96]=new Point(96,4,6*SQRT3);p[97]=new Point(97,6,6*SQRT3);p[135]=new Point(135,8,6*SQRT3);p[179]=new Point(179,10,6*SQRT3);
        p[93]=new Point(93,-2,6*SQRT3);p[92]=new Point(92,-4,6*SQRT3);p[91]=new Point(91,-6,6*SQRT3);p[168]=new Point(168,-8,6*SQRT3);p[215]=new Point(215,-10,6*SQRT3);
        p[112]=new Point(112,0,-6*SQRT3);p[111]=new Point(111,2,-6*SQRT3);p[110]=new Point(110,4,-6*SQRT3);p[109]=new Point(109,6,-6*SQRT3);p[147]=new Point(147,8,-6*SQRT3);p[191]=new Point(191,10,-6*SQRT3);
        p[113]=new Point(113,-2,-6*SQRT3);p[114]=new Point(114,-4,-6*SQRT3);p[115]=new Point(115,-6,-6*SQRT3);p[156]=new Point(156,-8,-6*SQRT3);p[203]=new Point(203,-10,-6*SQRT3);
        p[131]=new Point(131,1,7*SQRT3);p[132]=new Point(132,3,7*SQRT3);p[133]=new Point(133,5,7*SQRT3);p[134]=new Point(134,7,7*SQRT3);p[178]=new Point(178,9,7*SQRT3);
        p[130]=new Point(130,-1,7*SQRT3);p[129]=new Point(129,-3,7*SQRT3);p[128]=new Point(128,-5,7*SQRT3);p[127]=new Point(127,-7,7*SQRT3);p[216]=new Point(216,-9,7*SQRT3);
        p[151]=new Point(151,1,-7*SQRT3);p[150]=new Point(150,3,-7*SQRT3);p[149]=new Point(149,5,-7*SQRT3);p[148]=new Point(148,7,-7*SQRT3);p[192]=new Point(192,9,-7*SQRT3);
        p[152]=new Point(152,-1,-7*SQRT3);p[153]=new Point(153,-3,-7*SQRT3);p[154]=new Point(154,-5,-7*SQRT3);p[155]=new Point(155,-7,-7*SQRT3);p[202]=new Point(202,-9,-7*SQRT3);
        p[173]=new Point(173,0,8*SQRT3);p[174]=new Point(174,2,8*SQRT3);p[175]=new Point(175,4,8*SQRT3);p[176]=new Point(176,6,8*SQRT3);p[177]=new Point(177,8,8*SQRT3);
        p[172]=new Point(172,-2,8*SQRT3);p[171]=new Point(171,-4,8*SQRT3);p[170]=new Point(170,-6,8*SQRT3);p[169]=new Point(169,-8,8*SQRT3);
        p[197]=new Point(197,0,-8*SQRT3);p[196]=new Point(196,2,-8*SQRT3);p[195]=new Point(195,4,-8*SQRT3);p[194]=new Point(194,6,-8*SQRT3);p[193]=new Point(193,8,-8*SQRT3);
        p[198]=new Point(198,-2,-8*SQRT3);p[199]=new Point(199,-4,-8*SQRT3);p[200]=new Point(200,-6,-8*SQRT3);p[201]=new Point(201,-8,-8*SQRT3);
        for (int i = 0; i < 217; i++){
            this.p[i] = p[i];
        }
    }
}
