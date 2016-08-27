package me.dowen.solr.analyzers;

import java.util.Map;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

/**
 * 拼音转换中文原拼分词过滤器工厂类
 * @author liufl / 2014年7月2日
 */
public class PinyinChineseTransformTokenFilterFactory extends TokenFilterFactory {

	private boolean isOutChinese = false; // 是否输出原中文开关
	private int minTermLength = 2; // 中文词组长度过滤，默认超过2位长度的中文才转换拼音
	private boolean firstChar = false; // 拼音缩写开关，输出编写时不输出全拼音

	/**
	 * 构造器
	 * @param args
	 */
	public PinyinChineseTransformTokenFilterFactory(Map<String, String> args) {
		super(args);
		this.isOutChinese = getBoolean(args, "isOutChinese", false);
		this.minTermLength = getInt(args, "minTermLength", 2);
		if (!args.isEmpty())
			throw new IllegalArgumentException("Unknown parameters: " + args);
	}

	public TokenFilter create(TokenStream input) {
		return new PinyinTransformTokenFilter(input, firstChar,
				minTermLength, isOutChinese);
	}
}
