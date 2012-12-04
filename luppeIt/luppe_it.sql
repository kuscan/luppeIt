/*
Navicat MySQL Data Transfer

Source Server         : kuscan.com
Source Server Version : 50159
Source Host           : 94.73.146.247:3306
Source Database       : kuscandb

Target Server Type    : MYSQL
Target Server Version : 50159
File Encoding         : 65001

Date: 2012-12-04 03:05:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `action`
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`action_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of action
-- ----------------------------

-- ----------------------------
-- Table structure for `action_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `action_parameter`;
CREATE TABLE `action_parameter` (
  `action_parameter_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_parameter_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `action_parameter_type_id` int(11) NOT NULL,
  `action_id` int(11) NOT NULL,
  PRIMARY KEY (`action_parameter_id`),
  KEY `action_parameter_type_id_fk1` (`action_parameter_type_id`),
  KEY `action_parameter_action_id_fk1` (`action_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of action_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for `action_parameter_type`
-- ----------------------------
DROP TABLE IF EXISTS `action_parameter_type`;
CREATE TABLE `action_parameter_type` (
  `action_parameter_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_parameter_type_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `action_paramter_type_class` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `action_parameter_type_class` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`action_parameter_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of action_parameter_type
-- ----------------------------


-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `category_status_id` int(11) NOT NULL,
  PRIMARY KEY (`category_id`),
  KEY `category_status_id_fk1` (`category_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Automotive', '1');
INSERT INTO `category` VALUES ('2', 'World News', '1');
INSERT INTO `category` VALUES ('3', 'Politics', '1');
INSERT INTO `category` VALUES ('4', 'Arts & Culture', '1');
INSERT INTO `category` VALUES ('5', 'Business & Investing', '1');
INSERT INTO `category` VALUES ('6', 'Sports', '1');
INSERT INTO `category` VALUES ('7', 'Movies', '1');
INSERT INTO `category` VALUES ('8', 'Music', '1');
INSERT INTO `category` VALUES ('9', 'Technology', '1');
INSERT INTO `category` VALUES ('10', 'Science', '1');
INSERT INTO `category` VALUES ('11', 'Web', '1');
INSERT INTO `category` VALUES ('12', 'Social Media', '1');
INSERT INTO `category` VALUES ('13', 'Fashion', '1');
INSERT INTO `category` VALUES ('14', 'Health', '1');
INSERT INTO `category` VALUES ('15', 'Travel', '1');

-- ----------------------------
-- Table structure for `category_status`
-- ----------------------------
DROP TABLE IF EXISTS `category_status`;
CREATE TABLE `category_status` (
  `category_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`category_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of category_status
-- ----------------------------
INSERT INTO `category_status` VALUES ('1', 'active');
INSERT INTO `category_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `country`
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `country_code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', 'default', 'default');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `resource_status_id` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `resource_status_id` (`resource_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', 'npr.org', '1');

-- ----------------------------
-- Table structure for `resource_status`
-- ----------------------------
DROP TABLE IF EXISTS `resource_status`;
CREATE TABLE `resource_status` (
  `resource_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`resource_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource_status
-- ----------------------------
INSERT INTO `resource_status` VALUES ('1', 'active');
INSERT INTO `resource_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `rss_resource`
-- ----------------------------
DROP TABLE IF EXISTS `rss_resource`;
CREATE TABLE `rss_resource` (
  `rss_resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `rss_resource_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `parent_resource_id` int(11) NOT NULL,
  `rss_resource_status_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `next_feed_date` bigint(200) NOT NULL,
  `update_interval_minute` int(11) NOT NULL,
  PRIMARY KEY (`rss_resource_id`),
  KEY `parent_resource_id_fk1` (`parent_resource_id`),
  KEY `rss_resource_status_id_fk1` (`rss_resource_status_id`),
  KEY `rss_resource_category_fk1` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of rss_resource
-- ----------------------------
INSERT INTO `rss_resource` VALUES ('1', 'npr.org | Politics', 'http://www.npr.org/rss/rss.php?id=1014', '1', '1', '3', '1354584748638', '30');

-- ----------------------------
-- Table structure for `rss_resource_status`
-- ----------------------------
DROP TABLE IF EXISTS `rss_resource_status`;
CREATE TABLE `rss_resource_status` (
  `rss_resource_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `rss_resource_status_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`rss_resource_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of rss_resource_status
-- ----------------------------
INSERT INTO `rss_resource_status` VALUES ('1', 'active');
INSERT INTO `rss_resource_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `share`
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `share_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `description` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `url` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `luppe_count` int(11) NOT NULL DEFAULT '0',
  `dig_count` int(11) NOT NULL DEFAULT '0',
  `view_count` int(11) NOT NULL DEFAULT '0',
  `category_id` int(11) NOT NULL,
  `share_status_id` int(11) NOT NULL,
  `rss_resource_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `last_modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`share_id`),
  KEY `share_status_id` (`share_status_id`),
  KEY `resource_id` (`rss_resource_id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES ('1', 'Closing Tax Loopholes Easier In Theory Than In Political Practice', 'Republicans want to raise revenue by closing loopholes in the tax code instead of by raising rates. But tax breaks like the charitable deduction and the mortgage interest deduction come with interest groups willing to fight tooth and nail for them.', '<p>Republicans want to raise revenue by closing loopholes in the tax code instead of by raising rates. But tax breaks like the charitable deduction and the mortgage interest deduction come with interest groups willing to fight tooth and nail for them.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166416945\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166416945\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/blogs/itsallpolitics/2012/12/03/166416945/closing-tax-loopholes-easier-in-theory-than-in-political-practice?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354571760000');
INSERT INTO `share` VALUES ('2', 'Republicans Counter With $2.2 Trillion Deficit Plan', 'On Monday, House Speaker John Boehner answered criticisms that Republicans have not proposed a deficit plan to counter the one from President Obama which they find so objectionable. Boehner\'s plan takes elements from presidential nominee Mitt Romney\'s proposal. David Welna talks to Melissa Block about the counteroffer.', '<p>On Monday, House Speaker John Boehner answered criticisms that Republicans have not proposed a deficit plan to counter the one from President Obama which they find so objectionable. Boehner\'s plan takes elements from presidential nominee Mitt Romney\'s proposal. David Welna talks to Melissa Block about the counteroffer.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166429255\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166429255\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/03/166429255/republicans-counter-with-2-2-trillion-deficit-plan?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354564800000');
INSERT INTO `share` VALUES ('3', 'It\'s ScuttleButton Time!', 'You should solve this week\'s ScuttleButton puzzle NOW, rather than wait until after Monday\'s Giants-Redskins game, when it\'s likely a lot of my friendships will come to a sudden end.', '<p>You should solve this week\'s ScuttleButton puzzle NOW, rather than wait until after Monday\'s Giants-Redskins game, when it\'s likely a lot of my friendships will come to a sudden end.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166406400\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166406400\">&#187; Add to Del.icio.us</a></p><a rel=\"nofollow\" href=\"http://ad.doubleclick.net/jump/n6735.NPR/news_politics;sz=300x80;ord=1739277654\"><img alt=\"\" src=\"http://ad.doubleclick.net/ad/n6735.NPR/news_politics;sz=300x80;ord=1739277654\"/></a>', 'http://www.npr.org/blogs/politicaljunkie/2012/12/03/166406400/its-scuttlebutton-time?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354558260000');
INSERT INTO `share` VALUES ('4', 'Op-Ed: Obama Should Risk Going Off The \'Fiscal Cliff\'', 'Negotiations between the White House and Republican leaders have reached a stalemate over how best to avoid going off the so-called fiscal cliff. Robert Kuttner, founder and co-director of the American Prospect, argues that the president should hold his ground in this debate, even if it means triggering the tax hikes and spending cuts.', '<p>Negotiations between the White House and Republican leaders have reached a stalemate over how best to avoid going off the so-called fiscal cliff. Robert Kuttner, founder and co-director of the American Prospect, argues that the president should hold his ground in this debate, even if it means triggering the tax hikes and spending cuts.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166414591\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166414591\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/03/166414591/op-ed-go-over-the-fiscal-cliff?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354557600000');
INSERT INTO `share` VALUES ('5', 'Gov. Christie Re-Election Bid In N.J. Heads Up 2013 Election Calendar', 'Two gubernatorial contests — in New Jersey and Virginia — plus mayoral races in New York and Los Angeles head up the 2013 election calendar. Plus: Time to get rid of the Iowa straw poll? And who will succeed Jesse Jackson Jr.?', '<p>Two gubernatorial contests — in New Jersey and Virginia — plus mayoral races in New York and Los Angeles head up the 2013 election calendar. Plus: Time to get rid of the Iowa straw poll? And who will succeed Jesse Jackson Jr.?</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=165962234\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D165962234\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/blogs/politicaljunkie/2012/12/03/165962234/gov-christie-re-election-bid-in-n-j-heads-up-2013-election-calendar?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354534200000');
INSERT INTO `share` VALUES ('6', 'Pick A Number: Let\'s Play \'Cap Those Deductions\'', 'Mitt Romney may have lost the election, but the tax policy he floated is sticking with congressional Republicans. Rather than raising rates, the GOP would prefer to shrink or eliminate deductions. So what would that do to the deficit — and to the middle class?', '<p>Mitt Romney may have lost the election, but the tax policy he floated is sticking with congressional Republicans. Rather than raising rates, the GOP would prefer to shrink or eliminate deductions. So what would that do to the deficit — and to the middle class?</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166368685\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166368685\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/blogs/itsallpolitics/2012/12/03/166368685/pick-a-number-lets-play-cap-those-deductions?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354522980000');
INSERT INTO `share` VALUES ('7', 'Dallas Residents Weigh In On \'Fiscal Cliff\' Talks', 'The battle over the looming spending cuts and tax increases known as the \"fiscal cliff\" begins this week where it ended last week — deadlocked. While there is no agreement on how lawmakers should work out the details of a compromise, there is widespread consensus that a deal must get done for the good of the country.', '<p>The battle over the looming spending cuts and tax increases known as the \"fiscal cliff\" begins this week where it ended last week — deadlocked. While there is no agreement on how lawmakers should work out the details of a compromise, there is widespread consensus that a deal must get done for the good of the country.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166378494\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166378494\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/03/166378494/dallas-residents-weigh-in-on-fiscal-cliff-talks?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354522440000');
INSERT INTO `share` VALUES ('8', 'The Tax Battle Decoded: What $250,000 Gets You', 'As Congress and the White House battle over a tax and spending plan before the end of the year, one number is at the forefront: $250,000. That\'s the income level above which the White House wants taxes to rise. Host Guy Raz speaks with Reuters personal finance columnist Linda Stern about where that amount of money goes a long way, and where it doesn\'t. Plus NPR\'s Mara Liasson weighs in on the state of budget talks in Washington.', '<p>As Congress and the White House battle over a tax and spending plan before the end of the year, one number is at the forefront: $250,000. That\'s the income level above which the White House wants taxes to rise. Host Guy Raz speaks with Reuters personal finance columnist Linda Stern about where that amount of money goes a long way, and where it doesn\'t. Plus NPR\'s Mara Liasson weighs in on the state of budget talks in Washington.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166358735\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166358735\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/02/166358735/the-tax-battle-decoded-what-250-000-gets-you?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354478400000');
INSERT INTO `share` VALUES ('9', 'Busting The Filibuster: Setting New Terms', 'Democratic Sen. Tom Udall of New Mexico tells host Guy Raz that next month might be the perfect time for Democrats to try a controversial technique to reform the filibuster.', '<p>Democratic Sen. Tom Udall of New Mexico tells host Guy Raz that next month might be the perfect time for Democrats to try a controversial technique to reform the filibuster.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166358737\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166358737\">&#187; Add to Del.icio.us</a></p><a rel=\"nofollow\" href=\"http://ad.doubleclick.net/jump/n6735.NPR/news_politics;sz=300x80;ord=1756067162\"><img alt=\"\" src=\"http://ad.doubleclick.net/ad/n6735.NPR/news_politics;sz=300x80;ord=1756067162\"/></a>', 'http://www.npr.org/2012/12/02/166358737/busting-the-filibuster-setting-new-terms?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354478400000');
INSERT INTO `share` VALUES ('10', 'No Deal On \'Fiscal Cliff\' Without Tax Increase On Rich, Geithner Says', 'Despite differences with Republicans on spending cuts and revenue increases, the treasury secretary said the two sides were \"moving closer together\" on a solution for deficit reduction. House Speaker John Boehner said he did not share that optimistic assessment.end-of-year deadline', '<p>Despite differences with Republicans on spending cuts and revenue increases, the treasury secretary said the two sides were \"moving closer together\" on a solution for deficit reduction. House Speaker John Boehner said he did not share that optimistic assessment.end-of-year deadline</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166352424\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166352424\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/blogs/itsallpolitics/2012/12/02/166352424/no-deal-on-fiscal-cliff-without-tax-increase-on-rich-geithner-says?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354474260000');
INSERT INTO `share` VALUES ('11', 'Obama Not The First To Take Fiscal Fight On The Road', '<em>Weekend Edition</em> host Rachel Martin speaks with presidential historian Michael Beschloss about presidents in the past who have taken their policy agenda on the road, as President Obama is doing with his efforts to avert the so-called fiscal cliff.', '<p><em>Weekend Edition</em> host Rachel Martin speaks with presidential historian Michael Beschloss about presidents in the past who have taken their policy agenda on the road, as President Obama is doing with his efforts to avert the so-called fiscal cliff.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166336699\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166336699\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/02/166336699/obama-not-the-first-to-take-fiscal-fight-on-the-road?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354453200000');
INSERT INTO `share` VALUES ('12', 'No Nominees, But Obama Cabinet Already Has Critics', '<em>Weekend Edition</em> host Rachel Martin speaks with NPR national political correspondent Mara Liasson about President Obama\'s plans for cabinet appointments in his second term. Already, the president will have to find new heads for the State Department and the CIA, and he\'s run into early opposition from Republicans in the Senate.', '<p><em>Weekend Edition</em> host Rachel Martin speaks with NPR national political correspondent Mara Liasson about President Obama\'s plans for cabinet appointments in his second term. Already, the president will have to find new heads for the State Department and the CIA, and he\'s run into early opposition from Republicans in the Senate.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166336696\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166336696\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/02/166336696/no-nominees-but-obama-cabinet-already-has-critics?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354453200000');
INSERT INTO `share` VALUES ('13', 'A Month Away, Fiscal Deadline Looms With No Deal', '<em>Weekend Edition</em> host Rachel Martin speaks with NPR\'s Tamara Keith about the latest developments in the federal budget negotiations and the impending package of tax increases and spending cuts that are set to automatically go into effect unless Congress acts by Jan. 1, 2013.', '<p><em>Weekend Edition</em> host Rachel Martin speaks with NPR\'s Tamara Keith about the latest developments in the federal budget negotiations and the impending package of tax increases and spending cuts that are set to automatically go into effect unless Congress acts by Jan. 1, 2013.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166336688\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166336688\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/2012/12/02/166336688/a-month-away-fiscal-deadline-looms-with-no-deal?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354453200000');
INSERT INTO `share` VALUES ('14', 'The 3 Unofficial GOP Rules That Are Making A Deficit Deal Even Harder', 'The rules adopted by House GOP leaders in recent years are leaving Speaker John Boehner little room to maneuver. To reach an agreement to avoid the fiscal cliff, he may have to break at least one of them, analysts say.', '<p>The rules adopted by House GOP leaders in recent years are leaving Speaker John Boehner little room to maneuver. To reach an agreement to avoid the fiscal cliff, he may have to break at least one of them, analysts say.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166268748\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166268748\">&#187; Add to Del.icio.us</a></p>', 'http://www.npr.org/blogs/itsallpolitics/2012/12/02/166268748/the-3-unofficial-gop-rules-that-are-making-a-deficit-deal-even-harder?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354436400000');
INSERT INTO `share` VALUES ('15', 'Is The Voting Rights Act Outdated?', 'The most effective civil rights law in U.S. history faces its most serious challenge yet as the Supreme Court prepares to re-examine its constitutionality.', '<p>The most effective civil rights law in U.S. history faces its most serious challenge yet as the Supreme Court prepares to re-examine its constitutionality.</p><p><a href=\"http://www.npr.org/templates/email/emailAFriend.php?storyId=166226641\">&#187; E-Mail This</a>&#160;&#160;&#160;&#160; <a href=\"http://del.icio.us/post?url=http%3A%2F%2Fwww.npr.org%2Ftemplates%2Fstory%2Fstory.php%3FstoryId%3D166226641\">&#187; Add to Del.icio.us</a></p><a rel=\"nofollow\" href=\"http://ad.doubleclick.net/jump/n6735.NPR/news_politics;sz=300x80;ord=30886210\"><img alt=\"\" src=\"http://ad.doubleclick.net/ad/n6735.NPR/news_politics;sz=300x80;ord=30886210\"/></a>', 'http://www.npr.org/2012/12/01/166226641/is-the-voting-rights-act-outdated?ft=1&f=1014', '', '0', '0', '0', '3', '1', '1', '0', '1354418340000');

-- ----------------------------
-- Table structure for `share_photo`
-- ----------------------------
DROP TABLE IF EXISTS `share_photo`;
CREATE TABLE `share_photo` (
  `share_photo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `share_id` bigint(20) NOT NULL,
  PRIMARY KEY (`share_photo_id`),
  KEY `share_id_fk2` (`share_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of share_photo
-- ----------------------------

-- ----------------------------
-- Table structure for `share_status`
-- ----------------------------
DROP TABLE IF EXISTS `share_status`;
CREATE TABLE `share_status` (
  `share_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `share_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`share_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of share_status
-- ----------------------------
INSERT INTO `share_status` VALUES ('1', 'active');
INSERT INTO `share_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `share_tag`
-- ----------------------------
DROP TABLE IF EXISTS `share_tag`;
CREATE TABLE `share_tag` (
  `share_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `share_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `truth` int(11) NOT NULL,
  PRIMARY KEY (`share_tag_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tag_status_id` int(11) NOT NULL,
  PRIMARY KEY (`tag_id`),
  KEY `tag_status_id_fk1` (`tag_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'programming', '1');
INSERT INTO `tag` VALUES ('2', 'java', '1');
INSERT INTO `tag` VALUES ('3', 'computer', '1');
INSERT INTO `tag` VALUES ('4', 'engineering', '1');
INSERT INTO `tag` VALUES ('5', 'play framework', '1');

-- ----------------------------
-- Table structure for `tag_status`
-- ----------------------------
DROP TABLE IF EXISTS `tag_status`;
CREATE TABLE `tag_status` (
  `tag_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`tag_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag_status
-- ----------------------------
INSERT INTO `tag_status` VALUES ('1', 'active');
INSERT INTO `tag_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) DEFAULT NULL,
  `trust` int(11) NOT NULL DEFAULT '50',
  `user_status_id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  `user_type_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_status_id` (`user_status_id`),
  KEY `country_id` (`country_id`),
  KEY `user_type_id` (`user_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'faruk.kuscan@gmail.com', '123456', 'faruk.kuscan@gmail.com', '0', '0', '2', '1', '1');
INSERT INTO `user` VALUES ('7', 'kkflmed@gmail.com', '123456', 'kkflmed@gmail.com', '0', '0', '2', '1', '1');

-- ----------------------------
-- Table structure for `user_action_1`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_1`;
CREATE TABLE `user_action_1` (
  `user_action_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_action_id`),
  KEY `action_id_fk1` (`action_id`),
  KEY `user_id_fk1` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_action_1
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_parameter_value_1`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_parameter_value_1`;
CREATE TABLE `user_action_parameter_value_1` (
  `user_action_id` bigint(20) NOT NULL,
  `action_parameter_id` int(11) NOT NULL,
  `parameter_value` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_action_id`,`action_parameter_id`),
  KEY `action_parameter_id_fk1` (`action_parameter_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_action_parameter_value_1
-- ----------------------------

-- ----------------------------
-- Table structure for `user_confirmation`
-- ----------------------------
DROP TABLE IF EXISTS `user_confirmation`;
CREATE TABLE `user_confirmation` (
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `confirmation_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_confirmation
-- ----------------------------
INSERT INTO `user_confirmation` VALUES ('faruk.kuscan@gmail.com', 'hMXDnMToegOPwFHXwuR6');
INSERT INTO `user_confirmation` VALUES ('kkflmed@gmail.com', 'LtuW3WAzSIq7g5tiG7yS');

-- ----------------------------
-- Table structure for `user_status`
-- ----------------------------
DROP TABLE IF EXISTS `user_status`;
CREATE TABLE `user_status` (
  `user_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_status
-- ----------------------------
INSERT INTO `user_status` VALUES ('1', 'registered');
INSERT INTO `user_status` VALUES ('2', 'active');

-- ----------------------------
-- Table structure for `user_type`
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES ('1', 'default');
