package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum Champion
{
    AATROX(266),
    AHRI(103),
    AKALI(84),
    ALISTAR(12),
    AMUMU(32),
    ANIVIA(34),
    ANNIE(1),
    ASHE(22),
    AZIR(268),
    BARD(432),
    BLITZCRANK(53),
    BRAND(63),
    BRAUM(201),
    CAITLYN(51),
    CASSIOPEIA(69),
    CHO_GATH(31),
    CORKI(42),
    DARIUS(122),
    DIANA(131),
    DR_MUNDO(36),
    DRAVEN(119),
    EKKO(245),
    ELISE(60),
    EVELYNN(28),
    EZREAL(81),
    FIDDLESTICKS(9),
    FIORA(114),
    FIZZ(105),
    GALIO(3),
    GANGPLANK(41),
    GAREN(86),
    GNAR(150),
    GRAGAS(79),
    GRAVES(104),
    HECARIM(120),
    HEIMERDINGER(74),
    IRELIA(39),
    JANNA(40),
    JARVAN_IV(59),
    JAX(24),
    JAYCE(126),
    JINX(222),
    KALISTA(429),
    KARMA(43),
    KARTHUS(30),
    KASSADIN(38),
    KATARINA(55),
    KAYLE(10),
    KENNEN(85),
    KHAZIX(121),
    KINDRED(203),
    KLED(240),
    KOG_MAW(96),
    LEBLANC(7),
    LEESIN(64),
    LEONA(89),
    LISSANDRA(127),
    LUCIAN(236),
    LULU(117),
    LUX(99),
    MALPHITE(54),
    MALZAHAR(90),
    MAOKAI(57),
    MASTERYI(11),
    MISS_FORTUNE(21),
    MORDEKAISER(82),
    MORGANA(25),
    NAMI(267),
    NASUS(75),
    NAUTILUS(111),
    NIDALEE(76),
    NOCTURNE(56),
    NUNU(20),
    OLAF(2),
    ORIANNA(61),
    PANTHEON(80),
    POPPY(78),
    QUINN(133),
    RAMMUS(33),
    REK_SAI(421),
    RENEKTON(58),
    RENGAR(107),
    RIVEN(92),
    RUMBLE(68),
    RYZE(13),
    SEJUANI(113),
    SHACO(35),
    SHEN(98),
    SHYVANA(102),
    SINGED(27),
    SION(14),
    SIVIR(15),
    SKARNER(72),
    SONA(37),
    SORAKA(16),
    SWAIN(50),
    SYNDRA(134),
    TAHM_KENCH(223),
    TALON(91),
    TARIC(44),
    TEEMO(17),
    THRESH(412),
    TRISTANA(18),
    TRUNDLE(48),
    TRYNDAMERE(23),
    TWISTED_FATE(4),
    TWITCH(29),
    UDYR(77),
    URGOT(6),
    VARUS(110),
    VAYNE(67),
    VEIGAR(45),
    VEL_KOZ(161),
    VI(254),
    VIKTOR(112),
    VLADIMIR(8),
    VOLIBEAR(106),
    WARWICK(19),
    WUKONG(62),
    XERATH(101),
    XINZHAO(5),
    YASUO(157),
    YORICK(83),
    ZAC(154),
    ZED(238),
    ZIGGS(115),
    ZILEAN(26),
    ZYRA(143),;

    public static Optional<Champion> getFromId(final Integer id)
    {
        return Stream.of(Champion.values()).filter(t -> t.id.equals(id)).findFirst();
    }

    final Integer id;

    Champion(final Integer id)
    {
        this.id = id;
    }

    /**
     * Returns the id associated with this champion
     *
     * @return Long
     */
    public Integer getId()
    {
        return this.id;
    }

}
