package no.stelar7.api.r4j.tests.local;

import no.stelar7.api.r4j.basic.constants.types.lol.GameQueueType;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class EnumUpdateTest
{
    String source = "{\"6080\":{\"name\":\"Set Queue 2\",\"shortName\":\"Set Queue 2\",\"description\":\"Set Queue 2\",\"detailedDescription\":\"Set Queue 2\"},\"1021\":{\"name\":\"1v1\",\"shortName\":\"1v1\",\"description\":\"1v1\",\"detailedDescription\":\"\"},\"1020\":{\"name\":\"One For All\",\"shortName\":\"One For All\",\"description\":\"One For All\",\"detailedDescription\":\"\"},\"6090\":{\"name\":\"Set Queue 3\",\"shortName\":\"Set Queue 3\",\"description\":\"Set Queue 3\",\"detailedDescription\":\"Set Queue 3\"},\"1023\":{\"name\":\"2v2\",\"shortName\":\"2v2\",\"description\":\"2v2\",\"detailedDescription\":\"\"},\"1022\":{\"name\":\"3v3\",\"shortName\":\"3v3\",\"description\":\"3v3\",\"detailedDescription\":\"\"},\"1024\":{\"name\":\"4v4\",\"shortName\":\"4v4\",\"description\":\"4v4\",\"detailedDescription\":\"\"},\"1050\":{\"name\":\"Odyssey (Crewmember)\",\"shortName\":\"Odyssey (Crewmember)\",\"description\":\"Crewmember\",\"detailedDescription\":\"Odyssey (Crewmember)\"},\"1051\":{\"name\":\"Odyssey Medium 1v0\",\"shortName\":\"Odyssey Medium 1v0\",\"description\":\"Odyssey Medium 1v0\",\"detailedDescription\":\"Odyssey Medium 1v0\"},\"14\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Draft Pick\",\"detailedDescription\":\"Normal (Draft Pick)\"},\"440\":{\"name\":\"Ranked Flex\",\"shortName\":\"Ranked Flex\",\"description\":\"Ranked Flex\",\"detailedDescription\":\"\"},\"441\":{\"name\":\"Ranked Flex\",\"shortName\":\"Ranked Flex\",\"description\":\"Ranked Flex\",\"detailedDescription\":\"\"},\"442\":{\"name\":\"Ranked Flex\",\"shortName\":\"Ranked Flex\",\"description\":\"Ranked Flex\",\"detailedDescription\":\"\"},\"422\":{\"name\":\"Ranked Solo/Duo\",\"shortName\":\"Ranked Solo/Duo\",\"description\":\"Ranked Solo/Duo\",\"detailedDescription\":\"\"},\"1102\":{\"name\":\"2v0 (Ranked)\",\"shortName\":\"2v0 (Ranked)\",\"description\":\"2v0 (Ranked)\",\"detailedDescription\":\"\"},\"31\":{\"name\":\"Intro\",\"shortName\":\"Intro\",\"description\":\"Intro\",\"detailedDescription\":\"\"},\"452\":{\"name\":\"1v1\",\"shortName\":\"1v1\",\"description\":\"1v1\",\"detailedDescription\":\"\"},\"420\":{\"name\":\"Ranked Solo/Duo\",\"shortName\":\"Ranked Solo/Duo\",\"description\":\"Ranked Solo/Duo\",\"detailedDescription\":\"\"},\"451\":{\"name\":\"3v3\",\"shortName\":\"3v3\",\"description\":\"3v3\",\"detailedDescription\":\"\"},\"1100\":{\"name\":\"Teamfight Tactics (Ranked)\",\"shortName\":\"Teamfight Tactics (Ranked)\",\"description\":\"Ranked\",\"detailedDescription\":\"Teamfight Tactics (Ranked)\"},\"33\":{\"name\":\"Intermediate\",\"shortName\":\"Intermediate\",\"description\":\"Intermediate\",\"detailedDescription\":\"\"},\"421\":{\"name\":\"Ranked Solo/Duo\",\"shortName\":\"Ranked Solo/Duo\",\"description\":\"Ranked Solo/Duo\",\"detailedDescription\":\"\"},\"450\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"1101\":{\"name\":\"1v0 (Ranked)\",\"shortName\":\"1v0 (Ranked)\",\"description\":\"1v0 (Ranked)\",\"detailedDescription\":\"\"},\"32\":{\"name\":\"Beginner\",\"shortName\":\"Beginner\",\"description\":\"Beginner\",\"detailedDescription\":\"\"},\"92\":{\"name\":\"Level 100 Gauntlet\",\"shortName\":\"Level 100 Gauntlet\",\"description\":\"Level 100 Gauntlet\",\"detailedDescription\":\"\"},\"35\":{\"name\":\"Beginner\",\"shortName\":\"Beginner\",\"description\":\"Beginner\",\"detailedDescription\":\"\"},\"91\":{\"name\":\"The Teemoing\",\"shortName\":\"The Teemoing\",\"description\":\"The Teemoing\",\"detailedDescription\":\"\"},\"34\":{\"name\":\"Intro\",\"shortName\":\"Intro\",\"description\":\"Intro\",\"detailedDescription\":\"\"},\"90\":{\"name\":\"The Teemoing\",\"shortName\":\"The Teemoing\",\"description\":\"The Teemoing\",\"detailedDescription\":\"\"},\"1032\":{\"name\":\"Odyssey Intro 3v0\",\"shortName\":\"Odyssey Intro 3v0\",\"description\":\"Odyssey Intro 3v0\",\"detailedDescription\":\"Odyssey Intro 3v0\"},\"431\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"\"},\"97\":{\"name\":\"Ascension\",\"shortName\":\"Ascension\",\"description\":\"Ascension\",\"detailedDescription\":\"\"},\"36\":{\"name\":\"Intermediate\",\"shortName\":\"Intermediate\",\"description\":\"Intermediate\",\"detailedDescription\":\"\"},\"430\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"96\":{\"name\":\"Ascension\",\"shortName\":\"Ascension\",\"description\":\"Ascension\",\"detailedDescription\":\"\"},\"1030\":{\"name\":\"Odyssey (Intro)\",\"shortName\":\"Odyssey (Intro)\",\"description\":\"Intro\",\"detailedDescription\":\"Odyssey (Intro)\"},\"41\":{\"name\":\"Team\",\"shortName\":\"Team\",\"description\":\"Team\",\"detailedDescription\":\"\"},\"433\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"318\":{\"name\":\"AR Ultra Rapid Fire\",\"shortName\":\"ARURF\",\"description\":\"AR Ultra Rapid Fire\",\"detailedDescription\":\"\"},\"2020\":{\"name\":\"Tutorial Part 3\",\"shortName\":\"Tutorial Part 3\",\"description\":\"Tutorial Part 3\",\"detailedDescription\":\"\"},\"1031\":{\"name\":\"Odyssey Intro 1v0\",\"shortName\":\"Odyssey Intro 1v0\",\"description\":\"Odyssey Intro 1v0\",\"detailedDescription\":\"Odyssey Intro 1v0\"},\"460\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"42\":{\"name\":\"Team\",\"shortName\":\"Team\",\"description\":\"Team\",\"detailedDescription\":\"\"},\"432\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"\"},\"319\":{\"name\":\"AR Ultra Rapid Fire\",\"shortName\":\"ARURF\",\"description\":\"AR Ultra Rapid Fire\",\"detailedDescription\":\"\"},\"461\":{\"name\":\"Twisted Treeline 1v1\",\"shortName\":\"Twisted Treeline 1v1\",\"description\":\"Twisted Treeline 1v1\",\"detailedDescription\":\"Twisted Treeline 1v1\"},\"901\":{\"name\":\"ARURF 1v1\",\"shortName\":\"ARURF 1v1\",\"description\":\"ARURF 1v1\",\"detailedDescription\":\"\"},\"900\":{\"name\":\"AR Ultra Rapid Fire\",\"shortName\":\"ARURF\",\"description\":\"AR Ultra Rapid Fire\",\"detailedDescription\":\"\"},\"903\":{\"name\":\"ARURF 3v3\",\"shortName\":\"ARURF 3v3\",\"description\":\"ARURF 3v3\",\"detailedDescription\":\"\"},\"902\":{\"name\":\"ARURF 2v2\",\"shortName\":\"ARURF 2v2\",\"description\":\"ARURF 2v2\",\"detailedDescription\":\"\"},\"904\":{\"name\":\"ARURF 4v4\",\"shortName\":\"ARURF 4v4\",\"description\":\"ARURF 4v4\",\"detailedDescription\":\"\"},\"314\":{\"name\":\"Nexus Siege\",\"shortName\":\"Nexus Siege\",\"description\":\"Nexus Siege\",\"detailedDescription\":\"\"},\"315\":{\"name\":\"Nexus Siege\",\"shortName\":\"Nexus Siege\",\"description\":\"Nexus Siege\",\"detailedDescription\":\"\"},\"301\":{\"name\":\"Legend of the Poro King\",\"shortName\":\"Poro King\",\"description\":\"Poro King\",\"detailedDescription\":\"\"},\"1120\":{\"name\":\"Teamfight Tactics (Normal Hyper Roll)\",\"shortName\":\"Teamfight Tactics (Normal Hyper Roll)\",\"description\":\"Normal Hyper Roll\",\"detailedDescription\":\"Teamfight Tactics (Normal Hyper Roll)\"},\"316\":{\"name\":\"Definitely Not Dominion\",\"shortName\":\"Definitely Not Dominion\",\"description\":\"Definitely Not Dominion\",\"detailedDescription\":\"\"},\"300\":{\"name\":\"Legend of the Poro King\",\"shortName\":\"Poro King\",\"description\":\"Poro King\",\"detailedDescription\":\"\"},\"1121\":{\"name\":\"1v0 (Normal Hyper Roll)\",\"shortName\":\"1v0 (Normal Hyper Roll)\",\"description\":\"1v0 (Normal Hyper Roll)\",\"detailedDescription\":\"\"},\"317\":{\"name\":\"Definitely Not Dominion\",\"shortName\":\"Definitely Not Dominion\",\"description\":\"Definitely Not Dominion\",\"detailedDescription\":\"\"},\"1122\":{\"name\":\"2v0 (Normal Hyper Roll)\",\"shortName\":\"2v0 (Normal Hyper Roll)\",\"description\":\"2v0 (Normal Hyper Roll)\",\"detailedDescription\":\"\"},\"1132\":{\"name\":\"2v0 (Hyper Roll)\",\"shortName\":\"2v0 (Hyper Roll)\",\"description\":\"2v0 (Hyper Roll)\",\"detailedDescription\":\"\"},\"990\":{\"name\":\"Invasion (Onslaught)\",\"shortName\":\"Invasion (Onslaught)\",\"description\":\"Onslaught\",\"detailedDescription\":\"Invasion (Onslaught)\"},\"1131\":{\"name\":\"1v0 (Hyper Roll)\",\"shortName\":\"1v0 (Hyper Roll)\",\"description\":\"1v0 (Hyper Roll)\",\"detailedDescription\":\"\"},\"1130\":{\"name\":\"Teamfight Tactics (Hyper Roll)\",\"shortName\":\"Teamfight Tactics (Hyper Roll)\",\"description\":\"Hyper Roll\",\"detailedDescription\":\"Teamfight Tactics (Hyper Roll)\"},\"981\":{\"name\":\"Invasion (Normal)\",\"shortName\":\"Invasion (Normal)\",\"description\":\"Normal\",\"detailedDescription\":\"Invasion (Normal)\"},\"980\":{\"name\":\"Invasion (Normal)\",\"shortName\":\"Invasion (Normal)\",\"description\":\"Normal\",\"detailedDescription\":\"Invasion (Normal)\"},\"982\":{\"name\":\"Invasion (Normal)\",\"shortName\":\"Invasion (Normal)\",\"description\":\"Normal\",\"detailedDescription\":\"Invasion (Normal)\"},\"831\":{\"name\":\"Bots Intro 3v3\",\"shortName\":\"Bots Intro 3v3\",\"description\":\"Bots Intro 3v3\",\"detailedDescription\":\"Bots Intro 3v3\"},\"921\":{\"name\":\"Legend of the Poro King\",\"shortName\":\"Poro King\",\"description\":\"Poro King\",\"detailedDescription\":\"\"},\"830\":{\"name\":\"Intro\",\"shortName\":\"Intro\",\"description\":\"Intro\",\"detailedDescription\":\"\"},\"920\":{\"name\":\"Legend of the Poro King\",\"shortName\":\"Poro King\",\"description\":\"Poro King\",\"detailedDescription\":\"\"},\"1300\":{\"name\":\"Nexus Blitz\",\"shortName\":\"Nexus Blitz\",\"description\":\"Blind Pick\",\"detailedDescription\":\"\"},\"832\":{\"name\":\"Bots Intro 1v1\",\"shortName\":\"Bots Intro 1v1\",\"description\":\"Bots Intro 1v1\",\"detailedDescription\":\"Bots Intro 1v1\"},\"1301\":{\"name\":\"1v1\",\"shortName\":\"1v1\",\"description\":\"1v1\",\"detailedDescription\":\"1v1\"},\"1302\":{\"name\":\"2v2\",\"shortName\":\"2v2\",\"description\":\"2v2\",\"detailedDescription\":\"\"},\"1303\":{\"name\":\"3v3\",\"shortName\":\"3v3\",\"description\":\"3v3\",\"detailedDescription\":\"\"},\"1304\":{\"name\":\"4v4\",\"shortName\":\"4v4\",\"description\":\"4v4\",\"detailedDescription\":\"\"},\"840\":{\"name\":\"Beginner\",\"shortName\":\"Beginner\",\"description\":\"Beginner\",\"detailedDescription\":\"\"},\"910\":{\"name\":\"Ascension\",\"shortName\":\"Ascension\",\"description\":\"Ascension\",\"detailedDescription\":\"\"},\"841\":{\"name\":\"Bots Easy 3v3\",\"shortName\":\"Bots Easy 3v3\",\"description\":\"Bots Easy 3v3\",\"detailedDescription\":\"Bots Easy 3v3\"},\"911\":{\"name\":\"Ascension\",\"shortName\":\"Ascension\",\"description\":\"Ascension\",\"detailedDescription\":\"\"},\"842\":{\"name\":\"Bots Easy 1v1\",\"shortName\":\"Bots Easy 1v1\",\"description\":\"Bots Easy 1v1\",\"detailedDescription\":\"Bots Easy 1v1\"},\"2200\":{\"name\":\"Teamfight Tactics (Normal)\",\"shortName\":\"Teamfight Tactics (Normal)\",\"description\":\"Normal\",\"detailedDescription\":\"Teamfight Tactics (Normal)\"},\"100\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"820\":{\"name\":\"Beginner\",\"shortName\":\"Beginner\",\"description\":\"Beginner\",\"detailedDescription\":\"\"},\"701\":{\"name\":\"Clash\",\"shortName\":\"Clash\",\"description\":\"Clash\",\"detailedDescription\":\"\"},\"700\":{\"name\":\"Clash\",\"shortName\":\"Clash\",\"description\":\"Clash\",\"detailedDescription\":\"\"},\"1704\":{\"name\":\"4v0 (Arena)\",\"shortName\":\"4v0 (Arena)\",\"description\":\"4v0 (Arena)\",\"detailedDescription\":\"4v0 (Arena)\"},\"1700\":{\"name\":\"Arena\",\"shortName\":\"Arena\",\"description\":\"Arena\",\"detailedDescription\":\"Arena\"},\"6040\":{\"name\":\"Set Queue 1\",\"shortName\":\"Set Queue 1\",\"description\":\"Set Queue 1\",\"detailedDescription\":\"Set Queue 1\"},\"1701\":{\"name\":\"1v0 (Arena)\",\"shortName\":\"1v0 (Arena)\",\"description\":\"1v0 (Arena)\",\"detailedDescription\":\"1v0 (Arena)\"},\"1710\":{\"name\":\"Arena\",\"shortName\":\"Arena\",\"description\":\"Arena\",\"detailedDescription\":\"Arena\"},\"6050\":{\"name\":\"Set Queue 2\",\"shortName\":\"Set Queue 2\",\"description\":\"Set Queue 2\",\"detailedDescription\":\"Set Queue 2\"},\"1090\":{\"name\":\"Teamfight Tactics (Normal)\",\"shortName\":\"Teamfight Tactics (Normal)\",\"description\":\"Normal\",\"detailedDescription\":\"Teamfight Tactics (Normal)\"},\"6020\":{\"name\":\"Set Queue 2\",\"shortName\":\"Set Queue 2\",\"description\":\"Set Queue 2\",\"detailedDescription\":\"Set Queue 2\"},\"1091\":{\"name\":\"1v0\",\"shortName\":\"1v0\",\"description\":\"1v0\",\"detailedDescription\":\"\"},\"1092\":{\"name\":\"2v0\",\"shortName\":\"2v0\",\"description\":\"2v0\",\"detailedDescription\":\"\"},\"1093\":{\"name\":\"3v0\",\"shortName\":\"3v0\",\"description\":\"3v0\",\"detailedDescription\":\"\"},\"470\":{\"name\":\"Ranked Flex\",\"shortName\":\"Ranked Flex\",\"description\":\"Ranked Flex\",\"detailedDescription\":\"\"},\"1094\":{\"name\":\"4v0\",\"shortName\":\"4v0\",\"description\":\"4v0\",\"detailedDescription\":\"\"},\"62\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"63\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"6030\":{\"name\":\"Set Queue 3\",\"shortName\":\"Set Queue 3\",\"description\":\"Set Queue 3\",\"detailedDescription\":\"Set Queue 3\"},\"400\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Draft Pick\",\"detailedDescription\":\"Normal (Draft Pick)\"},\"401\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Draft Pick\",\"detailedDescription\":\"Normal (Draft Pick)\"},\"402\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Draft Pick\",\"detailedDescription\":\"Normal (Draft Pick)\"},\"64\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"403\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Draft Pick\",\"detailedDescription\":\"Normal (Draft Pick)\"},\"413\":{\"name\":\"Ranked\",\"shortName\":\"Ranked\",\"description\":\"Ranked Draft\",\"detailedDescription\":\"\"},\"65\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"1902\":{\"name\":\"URF 2v2\",\"shortName\":\"URF 2v2\",\"description\":\"URF 2v2\",\"detailedDescription\":\"\"},\"1041\":{\"name\":\"Odyssey Easy 1v0\",\"shortName\":\"Odyssey Easy 1v0\",\"description\":\"Odyssey Easy 1v0\",\"detailedDescription\":\"Odyssey Easy 1v0\"},\"412\":{\"name\":\"Ranked\",\"shortName\":\"Ranked\",\"description\":\"Ranked Draft\",\"detailedDescription\":\"\"},\"1903\":{\"name\":\"URF 3v3\",\"shortName\":\"URF 3v3\",\"description\":\"URF 3v3\",\"detailedDescription\":\"\"},\"1040\":{\"name\":\"Odyssey (Cadet)\",\"shortName\":\"Odyssey (Cadet)\",\"description\":\"Cadet\",\"detailedDescription\":\"Odyssey (Cadet)\"},\"411\":{\"name\":\"Ranked\",\"shortName\":\"Ranked\",\"description\":\"Ranked Draft\",\"detailedDescription\":\"\"},\"1900\":{\"name\":\"Ultra Rapid Fire\",\"shortName\":\"URF\",\"description\":\"Ultra Rapid Fire\",\"detailedDescription\":\"\"},\"0\":{\"name\":\"Custom\",\"shortName\":\"Custom\",\"description\":\"Custom\",\"detailedDescription\":\"\"},\"410\":{\"name\":\"Ranked\",\"shortName\":\"Ranked\",\"description\":\"Ranked Draft\",\"detailedDescription\":\"\"},\"1901\":{\"name\":\"URF 1v1\",\"shortName\":\"URF 1v1\",\"description\":\"URF 1v1\",\"detailedDescription\":\"\"},\"1\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"1010\":{\"name\":\"Snow Battle ARURF\",\"shortName\":\"Snow ARURF\",\"description\":\"Snow Battle ARURF\",\"detailedDescription\":\"\"},\"2\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"1011\":{\"name\":\"Snow Battle ARURF\",\"shortName\":\"Snow ARURF\",\"description\":\"Snow Battle ARURF\",\"detailedDescription\":\"\"},\"1012\":{\"name\":\"Snow Battle ARURF\",\"shortName\":\"Snow ARURF\",\"description\":\"Snow Battle ARURF\",\"detailedDescription\":\"\"},\"1160\":{\"name\":\"Teamfight Tactics (Double Up Workshop)\",\"shortName\":\"Teamfight Tactics (Double Up Workshop)\",\"description\":\"Double Up (Workshop)\",\"detailedDescription\":\"Teamfight Tactics (Double Up Workshop)\"},\"1904\":{\"name\":\"URF 4v4\",\"shortName\":\"URF 4v4\",\"description\":\"URF 4v4\",\"detailedDescription\":\"\"},\"1161\":{\"name\":\"2v0 (Double Up)\",\"shortName\":\"2v0 (Double Up)\",\"description\":\"2v0 (Double Up)\",\"detailedDescription\":\"2v0 (Double Up)\"},\"1070\":{\"name\":\"Odyssey (Onslaught)\",\"shortName\":\"Odyssey (Onslaught)\",\"description\":\"Onslaught\",\"detailedDescription\":\"Odyssey (Onslaught)\"},\"1162\":{\"name\":\"4v0 (Double Up)\",\"shortName\":\"4v0 (Double Up)\",\"description\":\"4v0 (Double Up)\",\"detailedDescription\":\"4v0 (Double Up)\"},\"1001\":{\"name\":\"Overcharge\",\"shortName\":\"Overcharge\",\"description\":\"Overcharge\",\"detailedDescription\":\"\"},\"1071\":{\"name\":\"Odyssey Uber 1v0\",\"shortName\":\"Odyssey Uber 1v0\",\"description\":\"Odyssey Uber 1v0\",\"detailedDescription\":\"Odyssey Uber 1v0\"},\"321\":{\"name\":\"Blood Moon\",\"shortName\":\"Blood Moon\",\"description\":\"Blood Moon\",\"detailedDescription\":\"\"},\"1000\":{\"name\":\"Overcharge\",\"shortName\":\"Overcharge\",\"description\":\"Overcharge\",\"detailedDescription\":\"\"},\"1172\":{\"name\":\"2v0 (Fortune's Favor)\",\"shortName\":\"2v0 (Fortune's Favor)\",\"description\":\"2v0 (Fortune's Favor)\",\"detailedDescription\":\"2v0 (Fortune's Favor)\"},\"320\":{\"name\":\"Blood Moon\",\"shortName\":\"Blood Moon\",\"description\":\"Blood Moon\",\"detailedDescription\":\"\"},\"8\":{\"name\":\"Normal\",\"shortName\":\"Normal\",\"description\":\"Blind Pick\",\"detailedDescription\":\"Normal (Blind Pick)\"},\"1171\":{\"name\":\"1v0 (Fortune's Favor)\",\"shortName\":\"1v0 (Fortune's Favor)\",\"description\":\"1v0 (Fortune's Favor)\",\"detailedDescription\":\"1v0 (Fortune's Favor)\"},\"9\":{\"name\":\"Ranked Flex\",\"shortName\":\"Ranked Flex\",\"description\":\"Ranked Flex\",\"detailedDescription\":\"\"},\"1170\":{\"name\":\"Teamfight Tactics (Fortune's Favor)\",\"shortName\":\"Teamfight Tactics (Fortune's Favor)\",\"description\":\"Fortune's Favor\",\"detailedDescription\":\"Teamfight Tactics (Fortune's Favor)\"},\"1061\":{\"name\":\"Odyssey Hard 1v0\",\"shortName\":\"Odyssey Hard 1v0\",\"description\":\"Odyssey Hard 1v0\",\"detailedDescription\":\"Odyssey Hard 1v0\"},\"325\":{\"name\":\"ARSR\",\"shortName\":\"ARSR\",\"description\":\"ARSR\",\"detailedDescription\":\"\"},\"1060\":{\"name\":\"Odyssey (Captain)\",\"shortName\":\"Odyssey (Captain)\",\"description\":\"Captain\",\"detailedDescription\":\"Odyssey (Captain)\"},\"324\":{\"name\":\"ARSR\",\"shortName\":\"ARSR\",\"description\":\"ARSR\",\"detailedDescription\":\"\"},\"1175\":{\"name\":\"1v7 Bots (Fortune's Favor)\",\"shortName\":\"1v7 Bots (Fortune's Favor)\",\"description\":\"1v7 Bots (Fortune's Favor)\",\"detailedDescription\":\"1v7 Bots (Fortune's Favor)\"},\"1111\":{\"name\":\"TFT Simulation\",\"shortName\":\"TFT Simulation\",\"description\":\"Simulation\",\"detailedDescription\":\"\"},\"1142\":{\"name\":\"2v0 (Normal Double Up)\",\"shortName\":\"2v0 (Normal Double Up)\",\"description\":\"2v0 (Normal Double Up)\",\"detailedDescription\":\"2v0 (Normal Double Up)\"},\"1110\":{\"name\":\"Teamfight Tactics (Tutorial)\",\"shortName\":\"Teamfight Tactics (Tutorial)\",\"description\":\"Tutorial\",\"detailedDescription\":\"\"},\"1143\":{\"name\":\"4v0 (Normal Double Up)\",\"shortName\":\"4v0 (Normal Double Up)\",\"description\":\"4v0 (Normal Double Up)\",\"detailedDescription\":\"4v0 (Normal Double Up)\"},\"1140\":{\"name\":\"Teamfight Tactics (Normal Double Up)\",\"shortName\":\"Teamfight Tactics (Normal Double Up)\",\"description\":\"Normal Double Up\",\"detailedDescription\":\"Teamfight Tactics (Normal Double Up)\"},\"490\":{\"name\":\"Quickplay\",\"shortName\":\"Quickplay\",\"description\":\"Quickplay\",\"detailedDescription\":\"Normal (Quickplay)\"},\"1141\":{\"name\":\"Teamfight Tactics (Double Up 1v7 Bots)\",\"shortName\":\"Teamfight Tactics (Double Up 1v7 Bots)\",\"description\":\"1v7 Bots (Double Up)\",\"detailedDescription\":\"Teamfight Tactics (Double Up 1v7 Bots)\"},\"52\":{\"name\":\"Beginner\",\"shortName\":\"Beginner\",\"description\":\"Beginner\",\"detailedDescription\":\"\"},\"1151\":{\"name\":\"2v0 (Double Up)\",\"shortName\":\"2v0 (Double Up)\",\"description\":\"2v0 (Double Up)\",\"detailedDescription\":\"2v0 (Double Up)\"},\"1150\":{\"name\":\"Teamfight Tactics (Double Up Workshop)\",\"shortName\":\"Teamfight Tactics (Double Up Workshop)\",\"description\":\"Double Up (Workshop)\",\"detailedDescription\":\"Teamfight Tactics (Double Up Workshop)\"},\"1182\":{\"name\":\"2v0 (Soul Brawl)\",\"shortName\":\"2v0 (Soul Brawl)\",\"description\":\"2v0 (Soul Brawl)\",\"detailedDescription\":\"2v0 (Soul Brawl)\"},\"1152\":{\"name\":\"4v0 (Double Up)\",\"shortName\":\"4v0 (Double Up)\",\"description\":\"4v0 (Double Up)\",\"detailedDescription\":\"4v0 (Double Up)\"},\"1180\":{\"name\":\"Teamfight Tactics (Soul Brawl)\",\"shortName\":\"Teamfight Tactics (Soul Brawl)\",\"description\":\"Soul Brawl\",\"detailedDescription\":\"Teamfight Tactics (Soul Brawl)\"},\"1181\":{\"name\":\"1v0 (Soul Brawl)\",\"shortName\":\"1v0 (Soul Brawl)\",\"description\":\"1v0 (Soul Brawl)\",\"detailedDescription\":\"1v0 (Soul Brawl)\"},\"1195\":{\"name\":\"1v7 Bots (Choncc's Treasure)\",\"shortName\":\"1v7 Bots (Choncc's Treasure)\",\"description\":\"1v7 Bots (Choncc's Treasure)\",\"detailedDescription\":\"1v7 Bots (Choncc's Treasure)\"},\"2000\":{\"name\":\"Tutorial Part 1\",\"shortName\":\"Tutorial Part 1\",\"description\":\"Tutorial Part 1\",\"detailedDescription\":\"\"},\"970\":{\"name\":\"Hexakill\",\"shortName\":\"Hexakill\",\"description\":\"Hexakill\",\"detailedDescription\":\"\"},\"1185\":{\"name\":\"1v7 Bots (Soul Brawl)\",\"shortName\":\"1v7 Bots (Soul Brawl)\",\"description\":\"1v7 Bots (Soul Brawl)\",\"detailedDescription\":\"1v7 Bots (Soul Brawl)\"},\"1191\":{\"name\":\"1v0 (Choncc's Treasure)\",\"shortName\":\"1v0 (Choncc's Treasure)\",\"description\":\"1v0 (Choncc's Treasure)\",\"detailedDescription\":\"1v0 (Choncc's Treasure)\"},\"971\":{\"name\":\"Hexakill 1v1\",\"shortName\":\"Hexakill 1v1\",\"description\":\"Hexakill 1v1\",\"detailedDescription\":\"Hexakill 1v1\"},\"1190\":{\"name\":\"Teamfight Tactics (Choncc's Treasure)\",\"shortName\":\"Teamfight Tactics (Choncc's Treasure)\",\"description\":\"Choncc's Treasure\",\"detailedDescription\":\"Teamfight Tactics (Choncc's Treasure)\"},\"1192\":{\"name\":\"2v0 (Choncc's Treasure)\",\"shortName\":\"2v0 (Choncc's Treasure)\",\"description\":\"2v0 (Choncc's Treasure)\",\"detailedDescription\":\"2v0 (Choncc's Treasure)\"},\"2010\":{\"name\":\"Tutorial Part 2\",\"shortName\":\"Tutorial Part 2\",\"description\":\"Tutorial Part 2\",\"detailedDescription\":\"\"},\"961\":{\"name\":\"The Teemoing\",\"shortName\":\"The Teemoing\",\"description\":\"The Teemoing\",\"detailedDescription\":\"\"},\"960\":{\"name\":\"The Teemoing\",\"shortName\":\"The Teemoing\",\"description\":\"The Teemoing\",\"detailedDescription\":\"\"},\"930\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"931\":{\"name\":\"ARAM\",\"shortName\":\"ARAM\",\"description\":\"ARAM\",\"detailedDescription\":\"\"},\"950\":{\"name\":\"Level 100 Gauntlet\",\"shortName\":\"Level 100 Gauntlet\",\"description\":\"Level 100 Gauntlet\",\"detailedDescription\":\"\"},\"1201\":{\"name\":\"1v1\",\"shortName\":\"1v1\",\"description\":\"1v1\",\"detailedDescription\":\"1v1\"},\"951\":{\"name\":\"Level 100 Gauntlet\",\"shortName\":\"Level 100 Gauntlet\",\"description\":\"Level 100 Gauntlet\",\"detailedDescription\":\"\"},\"1200\":{\"name\":\"Nexus Blitz\",\"shortName\":\"Nexus Blitz\",\"description\":\"Blind Pick\",\"detailedDescription\":\"\"},\"941\":{\"name\":\"Nexus Siege\",\"shortName\":\"Nexus Siege\",\"description\":\"Nexus Siege\",\"detailedDescription\":\"\"},\"940\":{\"name\":\"Nexus Siege\",\"shortName\":\"Nexus Siege\",\"description\":\"Nexus Siege\",\"detailedDescription\":\"\"},\"3000\":{\"name\":\"TFT Custom\",\"shortName\":\"TFT Custom\",\"description\":\"TFT Custom\",\"detailedDescription\":\"TFT Custom\"},\"852\":{\"name\":\"Bots Medium 1v1\",\"shortName\":\"Bots Medium 1v1\",\"description\":\"Bots Medium 1v1\",\"detailedDescription\":\"Bots Medium 1v1\"},\"851\":{\"name\":\"Bots Medium 3v3\",\"shortName\":\"Bots Medium 3v3\",\"description\":\"Bots Medium 3v3\",\"detailedDescription\":\"Bots Medium 3v3\"},\"850\":{\"name\":\"Intermediate\",\"shortName\":\"Intermediate\",\"description\":\"Intermediate\",\"detailedDescription\":\"\"},\"800\":{\"name\":\"Intermediate\",\"shortName\":\"Intermediate\",\"description\":\"Intermediate\",\"detailedDescription\":\"\"},\"801\":{\"name\":\"Co-op vs. AI\",\"shortName\":\"Co-op vs. AI\",\"description\":\"Co-op vs. AI\",\"detailedDescription\":\"\"},\"860\":{\"name\":\"ARAM 5v5 Bots\",\"shortName\":\"ARAM 5v5 Bots\",\"description\":\"ARAM 5v5 Bots\",\"detailedDescription\":\"ARAM 5v5 Bots\"},\"810\":{\"name\":\"Intro\",\"shortName\":\"Intro\",\"description\":\"Intro\",\"detailedDescription\":\"\"},\"1404\":{\"name\":\"4v4\",\"shortName\":\"4v4\",\"description\":\"4v4\",\"detailedDescription\":\"\"},\"1403\":{\"name\":\"3v3\",\"shortName\":\"3v3\",\"description\":\"3v3\",\"detailedDescription\":\"\"},\"1402\":{\"name\":\"2v2\",\"shortName\":\"2v2\",\"description\":\"2v2\",\"detailedDescription\":\"\"},\"1401\":{\"name\":\"1v1\",\"shortName\":\"1v1\",\"description\":\"1v1\",\"detailedDescription\":\"1v1\"},\"721\":{\"name\":\"Clash\",\"shortName\":\"Clash\",\"description\":\"Clash\",\"detailedDescription\":\"\"},\"1400\":{\"name\":\"Ultimate Spellbook\",\"shortName\":\"Ultimate Spellbook\",\"description\":\"Ultimate Spellbook\",\"detailedDescription\":\"\"},\"720\":{\"name\":\"Clash\",\"shortName\":\"Clash\",\"description\":\"Clash\",\"detailedDescription\":\"\"},\"3010\":{\"name\":\"TFT Hyper Roll Custom\",\"shortName\":\"TFT Hyper Roll Custom\",\"description\":\"TFT Hyper Roll Custom\",\"detailedDescription\":\"TFT Hyper Roll Custom\"},\"600\":{\"name\":\"Blood Moon\",\"shortName\":\"Blood Moon\",\"description\":\"Blood Moon\",\"detailedDescription\":\"\"},\"601\":{\"name\":\"Blood Moon\",\"shortName\":\"Blood Moon\",\"description\":\"Blood Moon\",\"detailedDescription\":\"\"},\"6060\":{\"name\":\"Set Queue 3\",\"shortName\":\"Set Queue 3\",\"description\":\"Set Queue 3\",\"detailedDescription\":\"Set Queue 3\"},\"6002\":{\"name\":\"2v0 (Set 3.5 Revival: Galaxies)\",\"shortName\":\"2v0 (Set 3.5 Revival: Galaxies)\",\"description\":\"2v0 (Set 3.5 Revival: Galaxies)\",\"detailedDescription\":\"2v0 (Set 3.5 Revival: Galaxies)\"},\"611\":{\"name\":\"Dark Star: Singularity\",\"shortName\":\"Dark Star\",\"description\":\"Dark Star: Singularity\",\"detailedDescription\":\"\"},\"6000\":{\"name\":\"Teamfight Tactics (Set 3.5 Revival: Galaxies)\",\"shortName\":\"Teamfight Tactics (Set 3.5 Revival: Galaxies)\",\"description\":\"Set 3.5 Revival: Galaxies\",\"detailedDescription\":\"Teamfight Tactics (Set 3.5 Revival: Galaxies)\"},\"610\":{\"name\":\"Dark Star: Singularity\",\"shortName\":\"Dark Star\",\"description\":\"Dark Star: Singularity\",\"detailedDescription\":\"\"},\"6001\":{\"name\":\"1v0 (Set 3.5 Revival: Galaxies)\",\"shortName\":\"1v0 (Set 3.5 Revival: Galaxies)\",\"description\":\"1v0 (Set 3.5 Revival: Galaxies)\",\"detailedDescription\":\"1v0 (Set 3.5 Revival: Galaxies)\"},\"6070\":{\"name\":\"Set Queue 1\",\"shortName\":\"Set Queue 1\",\"description\":\"Set Queue 1\",\"detailedDescription\":\"Set Queue 1\"},\"6005\":{\"name\":\"1v7 Bots (Set 3.5 Revival: Galaxies)\",\"shortName\":\"1v7 Bots (Set 3.5 Revival: Galaxies)\",\"description\":\"1v7 Bots (Set 3.5 Revival: Galaxies)\",\"detailedDescription\":\"1v7 Bots (Set 3.5 Revival: Galaxies)\"},\"6010\":{\"name\":\"Set Queue 1\",\"shortName\":\"Set Queue 1\",\"description\":\"Set Queue 1\",\"detailedDescription\":\"Set Queue 1\"}}";
    
    @Test
    public void test()
    {
        Map<String, Object> jsonTree = Utils.getGson().fromJson(source, HashMap.class);
        List<Integer> allQueues = jsonTree.keySet()
                                          .stream()
                                          .map(Integer::parseInt)
                                          .sorted(Comparator.comparingInt(o -> o))
                                          .collect(Collectors.toList());
        
        List<Integer> currentQueues = Arrays.stream(GameQueueType.values())
                                            .flatMap(type -> Arrays.stream(type.getValues()))
                                            .sorted(Comparator.comparingInt(o -> o))
                                            .collect(Collectors.toList());
        for (Integer queue : allQueues)
        {
            if (!currentQueues.contains(queue))
            {
                System.out.println("Missing queue: " + queue);
            }
        }
    }
}
