package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum ChampionType
{
    AATROX(266, "Aatrox"),
    AHRI(103, "Ahri"),
    AKALI(84, "Akali"),
    ALISTAR(12, "Alistar"),
    AMUMU(32, "Amumu"),
    ANIVIA(34, "Anivia"),
    ANNIE(1, "Annie"),
    ASHE(22, "Ashe"),
    AURELION_SOL(136, "Aurelion Sol"),
    AZIR(268, "Azir"),
    BARD(432, "Bard"),
    BLITZCRANK(53, "Blitzcrank"),
    BRAND(63, "Brand"),
    BRAUM(201, "Braum"),
    CAITLYN(51, "Caitlyn"),
    CAMILLE(164, "Camille"),
    CASSIOPEIA(69, "Cassiopeia"),
    CHO_GATH(31, "Cho'Gath"),
    CORKI(42, "Corki"),
    DARIUS(122, "Darius"),
    DIANA(131, "Diana"),
    DR_MUNDO(36, "Dr. Mundo"),
    DRAVEN(119, "Draven"),
    EKKO(245, "Ekko"),
    ELISE(60, "Elise"),
    EVELYNN(28, "Evelynn"),
    EZREAL(81, "Ezreal"),
    FIDDLESTICKS(9, "Fiddlesticks"),
    FIORA(114, "Fiora"),
    FIZZ(105, "Fizz"),
    GALIO(3, "Galio"),
    GANGPLANK(41, "Gangplank"),
    GAREN(86, "Garen"),
    GNAR(150, "Gnar"),
    GRAGAS(79, "Gragas"),
    GRAVES(104, "Graves"),
    HECARIM(120, "Hecarim"),
    HEIMERDINGER(74, "Heimerdinger"),
    ILLAOI(420, "Illaoi"),
    IRELIA(39, "Irelia"),
    IVERN(427, "Ivern"),
    JANNA(40, "Janna"),
    JARVAN_IV(59, "Jarvan IV"),
    JAX(24, "Jax"),
    JAYCE(126, "Jayce"),
    JHIN(202, "Jhin"),
    JINX(222, "Jinx"),
    KALISTA(429, "Kalista"),
    KARMA(43, "Karma"),
    KARTHUS(30, "Karthus"),
    KASSADIN(38, "Kassadin"),
    KATARINA(55, "Katarina"),
    KAYLE(10, "Kayle"),
    KENNEN(85, "Kennen"),
    KHA_ZIX(121, "Kha'Zix"),
    KINDRED(203, "Kindred"),
    KLED(240, "Kled"),
    KOG_MAW(96, "Kog'Maw"),
    LEBLANC(7, "LeBlanc"),
    LEE_SIN(64, "Lee Sin"),
    LEONA(89, "Leona"),
    LISSANDRA(127, "Lissandra"),
    LUCIAN(236, "Lucian"),
    LULU(117, "Lulu"),
    LUX(99, "Lux"),
    MALPHITE(54, "Malphite"),
    MALZAHAR(90, "Malzahar"),
    MAOKAI(57, "Maokai"),
    MASTER_YI(11, "Master Yi"),
    MISS_FORTUNE(21, "Miss Fortune"),
    MORDEKAISER(82, "Mordekaiser"),
    MORGANA(25, "Morgana"),
    NAMI(267, "Nami"),
    NASUS(75, "Nasus"),
    NAUTILUS(111, "Nautilus"),
    NIDALEE(76, "Nidalee"),
    NOCTURNE(56, "Nocturne"),
    NUNU(20, "Nunu"),
    OLAF(2, "Olaf"),
    ORIANNA(61, "Orianna"),
    PANTHEON(80, "Pantheon"),
    POPPY(78, "Poppy"),
    QUINN(133, "Quinn"),
    RAKAN(497, "Rakan"),
    RAMMUS(33, "Rammus"),
    REK_SAI(421, "Rek'Sai"),
    RENEKTON(58, "Renekton"),
    RENGAR(107, "Rengar"),
    RIVEN(92, "Riven"),
    RUMBLE(68, "Rumble"),
    RYZE(13, "Ryze"),
    SEJUANI(113, "Sejuani"),
    SHACO(35, "Shaco"),
    SHEN(98, "Shen"),
    SHYVANA(102, "Shyvana"),
    SINGED(27, "Singed"),
    SION(14, "Sion"),
    SIVIR(15, "Sivir"),
    SKARNER(72, "Skarner"),
    SONA(37, "Sona"),
    SORAKA(16, "Soraka"),
    SWAIN(50, "Swain"),
    SYNDRA(134, "Syndra"),
    TAHM_KENCH(223, "Tahm Kench"),
    TALIYAH(163, "Taliyah"),
    TALON(91, "Talon"),
    TARIC(44, "Taric"),
    TEEMO(17, "Teemo"),
    THRESH(412, "Thresh"),
    TRISTANA(18, "Tristana"),
    TRUNDLE(48, "Trundle"),
    TRYNDAMERE(23, "Tryndamere"),
    TWISTED_FATE(4, "Twisted Fate"),
    TWITCH(29, "Twitch"),
    UDYR(77, "Udyr"),
    URGOT(6, "Urgot"),
    VARUS(110, "Varus"),
    VAYNE(67, "Vayne"),
    VEIGAR(45, "Veigar"),
    VEL_KOZ(161, "Vel'Koz"),
    VI(254, "Vi"),
    VIKTOR(112, "Viktor"),
    VLADIMIR(8, "Vladimir"),
    VOLIBEAR(106, "Volibear"),
    WARWICK(19, "Warwick"),
    WUKONG(62, "Wukong"),
    XAYAH(498, "Xayah"),
    XERATH(101, "Xerath"),
    XIN_ZHAO(5, "Xin Zhao"),
    YASUO(157, "Yasuo"),
    YORICK(83, "Yorick"),
    ZAC(154, "Zac"),
    ZED(238, "Zed"),
    ZIGGS(115, "Ziggs"),
    ZILEAN(26, "Zilean"),
    ZYRA(143, "Zyra"),;
    
    
    private final Integer id;
    private final String  formatted;
    
    ChampionType(final Integer id, final String formatted)
    {
        this.id = id;
        this.formatted = formatted;
    }
    
    public static Optional<ChampionType> getFromCode(final String id)
    {
        return Stream.of(ChampionType.values()).filter(t -> t.id.equals(Integer.valueOf(id))).findFirst();
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
    
    public String getFormattedName()
    {
        return formatted;
    }
}
