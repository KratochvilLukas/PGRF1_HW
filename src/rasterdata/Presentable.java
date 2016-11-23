package rasterdata;

public interface Presentable<DeviceType> {
	DeviceType present(DeviceType device);
}
