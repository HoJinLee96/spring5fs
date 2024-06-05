package spring;

public class VersionPrinter {
	int MajonVersion;
	int MinorVersion;
	
	public VersionPrinter(int majonVersion, int minorVersion) {
		super();
		MajonVersion = majonVersion;
		MinorVersion = minorVersion;
	}

	public void print() {
		System.out.printf("현재 버전은 %d.%d 입니다.\n",MajonVersion,MinorVersion);
	}

}
