package com.tvd12.ezyfox.sfs2x.testing.any;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.tvd12.ezyfox.sfs2x.testing.InterfaceClassPairGenerater;

public class CommandPropertiesFileTest {

	@Test
	public void test() throws IOException {
		File file = new File(FileUtils.getFile("").getAbsolutePath() + "/src/main/resources/ezyfox/config/commands.properties");
		FileUtils.write(file, Base64Coder.encodeString(InterfaceClassPairGenerater.pairs("com.tvd12.ezyfox.sfs2x.command.impl")));
	}
	
	public static void main(String[] args) throws IOException {
		new CommandPropertiesFileTest().test();
	}
	
}
