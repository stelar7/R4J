package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Champion
{
    ALL(0L),
    AATROX(266L),
    AHRI(103L),
    AKALI(84L),
    ALISTAR(12L),
    AMUMU(32L),
    ANIVIA(34L),
    ANNIE(1L),
    ASHE(22L),
    AZIR(268L),
    BARD(432L),
    BLITZCRANK(53L),
    BRAND(63L),
    BRAUM(201L),
    CAITLYN(51L),
    CASSIOPEIA(69L),
    CHO_GATH(31L),
    CORKI(42L),
    DARIUS(122L),
    DIANA(131L),
    DR_MUNDO(36L),
    DRAVEN(119L),
    EKKO(245L),
    ELISE(60L),
    EVELYNN(28L),
    EZREAL(81L),
    FIDDLESTICKS(9L),
    FIORA(114L),
    FIZZ(105L),
    GALIO(3L),
    GANGPLANK(41L),
    GAREN(86L),
    GNAR(150L),
    GRAGAS(79L),
    GRAVES(104L),
    HECARIM(120L),
    HEIMERDINGER(74L),
    IRELIA(39L),
    JANNA(40L),
    JARVAN_IV(59L),
    JAX(24L),
    JAYCE(126L),
    JINX(222L),
    KALISTA(429L),
    KARMA(43L),
    KARTHUS(30L),
    KASSADIN(38L),
    KATARINA(55L),
    KAYLE(10L),
    KENNEN(85L),
    KHAZIX(121L),
    KINDRED(203L),
    KOG_MAW(96L),
    LEBLANC(7L),
    LEESIN(64L),
    LEONA(89L),
    LISSANDRA(127L),
    LUCIAN(236L),
    LULU(117L),
    LUX(99L),
    MALPHITE(54L),
    MALZAHAR(90L),
    MAOKAI(57L),
    MASTERYI(11L),
    MISS_FORTUNE(21L),
    MORDEKAISER(82L),
    MORGANA(25L),
    NAMI(267L),
    NASUS(75L),
    NAUTILUS(111L),
    NIDALEE(76L),
    NOCTURNE(56L),
    NUNU(20L),
    OLAF(2L),
    ORIANNA(61L),
    PANTHEON(80L),
    POPPY(78L),
    QUINN(133L),
    RAMMUS(33L),
    REK_SAI(421L),
    RENEKTON(58L),
    RENGAR(107L),
    RIVEN(92L),
    RUMBLE(68L),
    RYZE(13L),
    SEJUANI(113L),
    SHACO(35L),
    SHEN(98L),
    SHYVANA(102L),
    SINGED(27L),
    SION(14L),
    SIVIR(15L),
    SKARNER(72L),
    SONA(37L),
    SORAKA(16L),
    SWAIN(50L),
    SYNDRA(134L),
    TAHM_KENCH(223L),
    TALON(91L),
    TARIC(44L),
    TEEMO(17L),
    THRESH(412L),
    TRISTANA(18L),
    TRUNDLE(48L),
    TRYNDAMERE(23L),
    TWISTED_FATE(4L),
    TWITCH(29L),
    UDYR(77L),
    URGOT(6L),
    VARUS(110L),
    VAYNE(67L),
    VEIGAR(45L),
    VEL_KOZ(161L),
    VI(254L),
    VIKTOR(112L),
    VLADIMIR(8L),
    VOLIBEAR(106L),
    WARWICK(19L),
    WUKONG(62L),
    XERATH(101L),
    XINZHAO(5L),
    YASUO(157L),
    YORICK(83L),
    ZAC(154L),
    ZED(238L),
    ZIGGS(115L),
    ZILEAN(26L),
    ZYRA(143L),;

    Long id;

    Champion(final Long id)
    {
        this.id = id;
    }

    public static Champion getFromId(Long id)
    {
        return Stream.of(Champion.values()).filter(t -> t.id.equals(id)).findFirst().get();
    }

    /**
     * Returns the id associated with this champion
     *
     * @return Long
     */
    public Long getId()
    {
        return this.id;
    }

}
